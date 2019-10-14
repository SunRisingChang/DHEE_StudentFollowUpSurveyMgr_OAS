package com.dhee.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dhee.action.SuperAction;
import com.dhee.daofactory.DaoFactory;
import com.dhee.vo.Emp;

public class DownloadAction extends SuperAction {
	private String tempName;

	public String getTempName() {
		try {
			ServletActionContext.getResponse().setHeader("charset", "ISO8859-1");
			return new String(this.tempName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			return "获取文件名出现了错误!";
		}
	}

	public void setTempName(String tempName) {
	}

	public InputStream getDownloadFile() {
		return ServletActionContext.getServletContext().getResourceAsStream("/EepFile/" + tempName);
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("download");
		List<Object> list = new DaoFactory().getAdminImpl().selectEmp(id);
		tempName = ((Emp) list.get(0)).getEnclosure();
		return SUCCESS;
	}

}
