package com.gdut.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.gdut.util.UploadConfigurationRead;

public class UploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/*
	 * 如果每次只上传一个文件 就可以使用上面介绍的代码
	 * 
	 * 这里义的泛型集合，是为了上传多个文件，也可以用数组 如： privaet File [] file; private String []
	 * fileFileName; private String [] fileContentType;
	 */
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;

	@Action(value = "testUpload")
	public String upload() throws IOException {
		try {
			// 指定上传的位置
			String path = UploadConfigurationRead.getInstance()
					.getConfigItem("uploadFilePath").trim();
			for (int i = 0; i < file.size(); i++) {
				// 获取输入流
				InputStream is = new FileInputStream(file.get(i));
				byte[] buffer = new byte[1024];
				int length = 0;
				// 创建输出流对象
				OutputStream os = new FileOutputStream(new File(path, this
						.getFileFileName().get(i)));
				// 开始上传
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				// 一次关闭流
				os.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			String string = "error";
			return ajaxJson(string);
		}
		// 返回结果
		String string = "success";
		return ajaxJson(string);

	}

	/*
	 * 为什么要定义fileFileName 和filecontextType 这里是用于FileUpload的拦截器的赋值
	 */
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

}
