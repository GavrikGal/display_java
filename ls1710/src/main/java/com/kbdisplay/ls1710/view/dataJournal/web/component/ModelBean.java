package com.kbdisplay.ls1710.view.dataJournal.web.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ModelType;
import com.kbdisplay.ls1710.service.data.ModelTypeService;

/**
 * вспомогательный класс для добавления нового изделия в БД.
 *
 * @author Gavrik
 *
 */
@ManagedBean(name = "modelBean")
@SessionScoped
public class ModelBean implements Serializable {

	@ManagedProperty(value = "#{modelTypeService}")
	private ModelTypeService modelTypeService;

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

	private List<ModelType> modelTypes;
	private ModelType selectedModelType;

	private List<Document> documents;
	private Document selectedDocument;


	public ModelBean() {
		photoNames = new HashSet<String>();
		newPhotoNames = new HashSet<String>();

	}


	public void fileUploadListener(final FileUploadEvent e) {

		try {
			InputStream stream = e.getFile().getInputstream();
			// String imageFormat =
			// FilenameUtils.getExtension(e.getFile().getFileName());
			// String imageName =
			// FilenameUtils.getBaseName(e.getFile().getFileName());
			File imageTempFile =
					new File("c:\\webapp\\upload\\" + e.getFile().getFileName());
			// TODO добавить файл настроек и брать путь для сохранения файла из
			// него

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

	public void clearNewPhoto() {
		if (!newPhotoNames.isEmpty()) {
			for (String newPhotoName : newPhotoNames) {
				removeFile(newPhotoName);
			}
			newPhotoNames.clear();
		}
	}

	public void addNewPhoto() {
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

	public ModelTypeService getModelTypeService() {
		return modelTypeService;
	}

	public void setModelTypeService(ModelTypeService modelTypeService) {
		this.modelTypeService = modelTypeService;
	}

	public Set<String> getNewPhotoNames() {
		return newPhotoNames;
	}

	public void setNewPhotoNames(Set<String> newPhotoNames) {
		this.newPhotoNames = newPhotoNames;
	}

	public List<ModelType> getModelTypes() {
		return modelTypes;
	}

	public void setModelTypes(List<ModelType> modelTypes) {
		this.modelTypes = modelTypes;
	}

	public ModelType getSelectedModelType() {
		return selectedModelType;
	}

	public void setSelectedModelType(ModelType selectedModelType) {
		this.selectedModelType = selectedModelType;
	}


	public List<Document> getDocuments() {
		return documents;
	}


	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}


	public Document getSelectedDocument() {
		return selectedDocument;
	}


	public void setSelectedDocument(Document selectedDocument) {
		this.selectedDocument = selectedDocument;
	}



}
