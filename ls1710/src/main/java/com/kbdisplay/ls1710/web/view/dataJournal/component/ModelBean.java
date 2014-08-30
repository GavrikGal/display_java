package com.kbdisplay.ls1710.web.view.dataJournal.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;

/**
 * вспомогательный класс для добавления нового изделия в БД.
 *
 * @author Gavrik
 *
 */
@Component("modelBean")
public class ModelBean implements Serializable {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = -541017713073302464L;

	/**
	 * добавляемая в БД модель.
	 */
	private ModelOfEquipment model;

	private Set<String> photoNames;
	private Set<String> newPhotoNames;


	public ModelBean() {
		photoNames = new HashSet<String>();
		newPhotoNames = new HashSet<String>();
	}

	public void fileUploadListener(final FileUploadEvent e) {



		try {
			InputStream stream = e.getFile().getInputstream();
//			String imageFormat = FilenameUtils.getExtension(e.getFile().getFileName());
//			String imageName = FilenameUtils.getBaseName(e.getFile().getFileName());
			File imageTempFile = new File("c:\\webapp\\upload\\" + e.getFile().getFileName());
			// TODO добавить файл настроек и брать путь для сохранения файла из него

			InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = stream;
		        os = new FileOutputStream(imageTempFile);
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }

			newPhotoNames.add(e.getFile().getFileName());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void clearNewPhoto(){
		if (!newPhotoNames.isEmpty()) {
			for (String newPhotoName : newPhotoNames) {
				removeFile(newPhotoName);
			}
			newPhotoNames.clear();
		}
	}

	public void addNewPhoto(){
		if (!newPhotoNames.isEmpty()) {
			photoNames.addAll(newPhotoNames);
			newPhotoNames.clear();
		}
	}

	public void removePhoto(final String photoName) {
		photoNames.remove(photoName);
		removeFile(photoName);
	}

	public int photoCount() {
		return photoNames.size();
	}

	private void removeFile(final String fileName) {
		File removingFile = new File("c:\\webapp\\upload\\" + fileName);
		removingFile.delete();
	}

	/*
	 * геттеры и сеттеры.
	 */
	public ModelOfEquipment getModel() {
		return model;
	}

	public void setModel(final ModelOfEquipment model) {
		this.model = model;
	}

	public List<String> getPhotoNames() {
		return Lists.newArrayList(photoNames);
	}

	public void setPhotoNames(final Set<String> photoNames) {
		this.photoNames = photoNames;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
