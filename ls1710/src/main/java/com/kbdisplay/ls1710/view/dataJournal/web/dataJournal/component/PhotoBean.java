package com.kbdisplay.ls1710.view.dataJournal.web.dataJournal.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "photoBean")
@RequestScoped
public class PhotoBean {

	private StreamedContent photo;


	public PhotoBean() {
		FacesContext context = FacesContext.getCurrentInstance();



		String photoName =
				context.getExternalContext().getRequestParameterMap()
						.get("photoName");
		String photoType = context.getExternalContext().getRequestParameterMap()
				.get("photoType");



		if ((photoName == null) || (photoName == "")) {
			photoName = "defaultPhoto.png";
			photoType = "image/png";
		}

		if ((photoType == null) || (photoType == "")) {
			photoType = "image/jpg";
		}

		try {

			photo =
					new DefaultStreamedContent(new FileInputStream(
							"c:\\webapp\\upload\\" + photoName),
							photoType);
		} catch (FileNotFoundException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}


	}

	public StreamedContent getPhoto() {
		return photo;
	}

	public void setPhoto(final StreamedContent photo) {
		this.photo = photo;
	}

}
