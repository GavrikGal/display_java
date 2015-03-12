package com.kbdisplay.ls1710.service.file.jpa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.service.file.FileFinderService;

@Service("fileFinder")
@Transactional
public class FileFinderServiceImpl implements FileFinderService {
	 //������ ��� ������ � ����������� �����������
    private Pattern p = null;
    private Matcher m = null;

    //����� ������ ��������� ������
    private long totalLength = 0;
    //����� ���������� ��������� ������
    private long filesNumber = 0;
    //����� ���������� ������������� ����������
    private long directoriesNumber = 0;

    //��������� ��� ����������� ��������, ������� ����� �����
    private final int FILES = 0;
    private final int DIRECTORIES = 1;
    private final int ALL = 2;

    /** ������� ����� ���������� FileFinder */
    public FileFinderServiceImpl() {
    }

    /**
     * ���� ����� ��������� ����� ���� �������� (������ � ����������),
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @return ������ (List) ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     */
    @Override
	public List<File> findAll(String startPath) throws Exception {
        return find(startPath, "", ALL);
    }

    /**
     * ���� ����� ��������� ����� �������� (������ � ����������),
     * ������� ������������� ��������� ����������� ��������� (mask),
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @param mask ���������� ���������, �������� ������ ���������������
     * ����� ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     * @return ������ (List) ��������� ��������
     */
    @Override
	public List<File> findAll(String startPath, String mask)
            throws Exception {
        return find(startPath, mask, ALL);
    }

    /**
     * ���� ����� ��������� ����� ���� ������,
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @return ������ (List) ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     */
    @Override
	public List<File> findFiles(String startPath)
            throws Exception {
        return find(startPath, "", FILES);
    }

    /**
     * ���� ����� ��������� ����� ������,
     * ������� ������������� ��������� ����������� ��������� (mask),
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @param mask ���������� ���������, �������� ������ ���������������
     * ����� ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     * @return ������ (List) ��������� ��������
     */
    @Override
	public List<File> findFiles(String startPath, String mask)
            throws Exception {
        return find(startPath, mask, FILES);
    }

    /**
     * ���� ����� ��������� ����� ���� ���������� (�����),
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @return ������ (List) ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     */
    @Override
	public List<File> findDirectories(String startPath)
            throws Exception {
        return find(startPath, "", DIRECTORIES);
    }

    /**
     * ���� ����� ��������� ����� ���������� (�����),
     * ������� ������������� ��������� ����������� ��������� (mask),
     * ������� � �������� ���������� (startPath)
     * @param startPath ��������� ���������� ������
     * @param mask ���������� ���������, �������� ������ ���������������
     * ����� ��������� ��������
     * @throws java.lang.Exception ���� �������� ������ � �������� ������
     * @return ������ (List) ��������� ��������
     */
    @Override
	public List<File> findDirectories(String startPath, String mask)
            throws Exception {
        return find(startPath, mask, DIRECTORIES);
    }

    /**
     * ���������� ��������� ������ ��������� ������
     * @return ������ ��������� ������ (����)
     */
    @Override
	public long getDirectorySize() {
        return totalLength;
    }

    /**
     * ���������� ����� ���������� ��������� ������
     * @return ���������� ��������� ������
     */
    @Override
	public long getFilesNumber() {
        return filesNumber;
    }

    /**
     * ���������� ����� ���������� ��������� ���������� (�����)
     * @return ���������� ��������� ���������� (�����)
     */
    @Override
	public long getDirectoriesNumber() {
        return directoriesNumber;
    }

    /*
    ���������, ������������� �� ��� ����� ���������
    ����������� ���������. ���������� true, ���� ���������
    ������ ������������� ����������� ���������, false - �
    ��������� ������.
    */
    private boolean accept(String name) {
        //���� ���������� ��������� �� ������...
        if(p == null) {
            //...������ ������ ��������
            return true;
        }
        //������� Matcher
        m = p.matcher(name);
        //��������� ��������
        if(m.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
    ���� ����� ��������� ��������� ��������� ������.
    ����� �������� ����� search ��� ���������� ������.
    */
    private List<File> find(String startPath, String mask, int objectType)
            throws Exception {
        //�������� ����������
        if(startPath == null || mask == null) {
            throw new Exception("������: �� ������ ��������� ������");
        }
        File topDirectory = new File(startPath);
        if(!topDirectory.exists()) {
            throw new Exception("������: ��������� ���� �� ����������");
        }
        //���� ������ ���������� ���������, ������� Pattern
        if(!mask.equals("")) {
            p = Pattern.compile(mask,
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }
        //�������� ��� ��������
        filesNumber = 0;
        directoriesNumber = 0;
        totalLength = 0;
        //������� ������ �����������
        ArrayList<File> res = new ArrayList<File>(100);

        //��������� �����
        search(topDirectory, res, objectType);

        //����������� null �������, �.�. ��� ��������� ������ find...
        //���������� ��������� ����� ���� �� ������
        p = null;
        //���������� ���������
        return res;
    }

    /*
    ���� ����� ��������� ����� �������� ��������� ����.
    ����, � �������� ������, ��������� ��������� ����������
    (�����), �� ���������� �������� ��� ����.
    ���������� ������ ����������� � ��������� res.
    ������� ���������� - topDirectory.
    ��� ������� (���� ��� ����������) - objectType.
    */
    private void search(File topDirectory, List<File> res, int objectType) {
        //�������� ������ ���� �������� � ������� ����������
        File[] list = topDirectory.listFiles();
        //������������� ��� ������� ��-�������
        for(int i = 0; i < list.length; i++) {
            //���� ��� ���������� (�����)...
            if(list[i].isDirectory()) {
                //...��������� �������� �� ������������ ���� �������
                // � ����������� ���������...
                if(objectType != FILES && accept(list[i].getName())) {
                    //...��������� ������� ������ � ������ �����������,
                    //� ��������� �������� ���������
                    directoriesNumber++;
                    res.add(list[i]);
                }
                //��������� ����� �� ��������� �����������
                search(list[i], res, objectType);
            }
            //���� ��� ����
            else {
                //...��������� �������� �� ������������ ���� �������
                // � ����������� ���������...
                if(objectType != DIRECTORIES && accept(list[i].getName())) {
                    //...��������� ������� ������ � ������ �����������,
                    //� ��������� �������� ���������
                    filesNumber++;
                    totalLength += list[i].length();
                    res.add(list[i]);
                }
            }
        }
    }
}
