package com.gdut.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.gdut.util.UploadConfigurationRead;
import org.apache.struts2.ServletActionContext;

public class DownloadAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -127008120349213613L;
	private String fileName;
	private String fileRealName;
	public void setFileName(){

		String fname = ServletActionContext.getRequest().getParameter("name");
		try{
			fname = new String(fname.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e){
			e.printStackTrace();
		}
		this.fileName = fname;
		this.fileRealName = fname;
	}


	public String getFileName() throws UnsupportedEncodingException{

		fileRealName = new String(fileRealName.getBytes(), "ISO-8859-1");
		return fileRealName;
	}

	public InputStream getDownloadFile(){

		this.setFileName();
		return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
	}

}