package com.dhee.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import com.dhee.action.SuperAction;

public class UploadAction extends SuperAction {
	/*
	 * 前端的form表单，其属性method="post",enctype = "multipart/form-data"的格式必须是此格式
	 * 前端的input标签，浏览本地文件的input标签其属性type = "file",name = "file"的格式必须是次格式
	 * 它的name属性与后台的fileFileName和fileContentType是搭配命名的
	 */
	// 传过来的文件
	private File file;

	// 提交过来的file名字：格式固定必须是<input type = "file" name = "file">这个的name属性+FIleName
	private String fileFileName;

	// 提交过来的file类型：格式固定必须是<input type = "file" name =
	// "file">这个的name属性+ContentType
	private String fileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void updata() throws Exception {
		String DBFileURLName = request.getParameter("EmpName") + "-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String root = ServletActionContext.getServletContext().getRealPath("/EepFile");
		// 按照字节流输入输出
		InputStream is = new FileInputStream(file);
		OutputStream os = new FileOutputStream(new File(root, DBFileURLName + ".rar"));
		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("{\"DBFileURLName\": \"" + DBFileURLName + ".rar\"}");
	}

}
