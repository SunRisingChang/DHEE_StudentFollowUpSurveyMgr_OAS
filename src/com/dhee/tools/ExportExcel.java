package com.dhee.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import com.dhee.vo.Show;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ExportExcel extends ActionSupport {

	
	public HSSFWorkbook execute(EmpUserPage empUserPage){
		//获得excel模板
		String sourceFilePath =ServletActionContext.getServletContext().getRealPath("/EXL") + "\\EmpInfo.xls";
		//文件连接器
		POIFSFileSystem fs;
		//excel工作簿
		HSSFWorkbook wb=null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(sourceFilePath));
			wb = new HSSFWorkbook(fs);
			//获得excel的sheet页
			HSSFSheet sheet = wb.getSheetAt(0);
			// 添加空行
			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;
			HSSFCell sourceCell = null;
			HSSFCell targetCell = null;
			int num = empUserPage.getPagelist().size() - 4;// 表中留有两个空行，只需插入list.size()-2个空行就行
			if (num > 0) {
				for (int j = 1; j <= num; j++) {
					sheet.shiftRows(4 + j, 20 + j, 1, true, false); // 底部数据向下移动一行
					sourceRow = sheet.getRow(1 + j); // 源行
					targetRow = sheet.getRow(4 + j); // 新加入的行
					for (int i = sourceRow.getFirstCellNum(); i < sourceRow.getLastCellNum(); i++) {
						sourceCell = sourceRow.getCell(i);
						targetCell = targetRow.createCell(i);
						targetCell.setCellStyle(sourceCell.getCellStyle());
						targetCell.setCellType(sourceCell.getCellType());
					}
				}
			}
			for (int i = 0; i < empUserPage.getPagelist().size(); i++) {
				Show show1 = (Show) empUserPage.getPagelist().get(i);
				// 设置序号的值
				HSSFRow row = sheet.getRow(2 + i);
				HSSFCell cell = row.getCell(0);
				cell.setCellValue(show1.getCompany());
				
				//设置姓名的值 
				row = sheet.getRow(2+i);
				cell = row.getCell(1);
				cell.setCellValue(i + 1);
				
				//设置性别的值
				row = sheet.getRow(2+i);
				cell = row.getCell(2);
				cell.setCellValue(show1.getName());
				
				//设置电话号码的值
				row = sheet.getRow(2+i);
				cell = row.getCell(3);
				cell.setCellValue(show1.getSex().equals("TT")?"男":"女");
				
				//设置招聘类型的值
				row = sheet.getRow(2+i);
				cell = row.getCell(4);
				cell.setCellValue(show1.getSkill().equals("defult")?"":show1.getSkill());
				
				//设置HR的值
				row = sheet.getRow(2+i);
				cell = row.getCell(5);
				cell.setCellValue(show1.getWorkplace().equals("--")?"":show1.getWorkplace());
				
				//设置技术方向的值
				row = sheet.getRow(2+i);
				cell = row.getCell(6);
				cell.setCellValue(show1.getRecruitmenttype().equals("defult")?"":show1.getRecruitmenttype());
				
				//设置毕业院校的值
				row = sheet.getRow(2+i);
				cell = row.getCell(7);
				cell.setCellValue(show1.getHr().equals("defult")?"":show1.getHr());
				
				//设置毕业时间的值
				row = sheet.getRow(2+i);
				cell = row.getCell(8);
				cell.setCellValue(show1.getTelphone());
				
				//设置学历的值
				row = sheet.getRow(2+i);
				cell = row.getCell(9);
				cell.setCellValue((show1.getHopesalary()==0.0)?"":show1.getHopesalary().toString());
				
				//设置到岗客户
				row = sheet.getRow(2+i);
				cell = row.getCell(10);
				cell.setCellValue(show1.getGraduateschool());
				
				//设置工作地点
				row = sheet.getRow(2+i);
				cell = row.getCell(11);
				cell.setCellValue("0001-01-01".equals(show1.getGraduatetime().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getGraduatetime()))?"":show1.getGraduatetime().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getGraduatetime()));
				
				//设置入职日期
				row = sheet.getRow(2+i);
				cell = row.getCell(12);
				cell.setCellValue(show1.getEducation().equals("defult")?"":show1.getEducation());
				
				//设置离职日期
				row = sheet.getRow(2+i);
				cell = row.getCell(13);
				cell.setCellValue("0001-01-01".equals(show1.getEntrytime().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getEntrytime()))?"":show1.getEntrytime().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getEntrytime()));
				

				//设置薪资
				row = sheet.getRow(2+i); 
				cell = row.getCell(14); 
				cell.setCellValue("0001-01-01".equals(show1.getContractperiod().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getContractperiod()))?"":show1.getContractperiod().equals("-1")?"":new SimpleDateFormat("yyyy-MM-dd").format(show1.getContractperiod()));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}

}
