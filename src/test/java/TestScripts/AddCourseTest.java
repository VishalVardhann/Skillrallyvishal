package TestScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities.IConstantPath;
//This is seleniumScript
public class AddCourseTest extends BaseClass {
	@Test
	public void addCourseTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCourseListLink();
		soft.assertTrue(courseList.getPageHeader().contains("Course List"));
		courseList.clickNewButton();
		soft.assertEquals(addCourse.getPageHeader(), "Add New Course");
		
		Map<String , String >map = excel.readFromExcel("Add Course");
		addCourse.setName(map.get("Name"));
		addCourse.selectCategory(web, map.get("Category"));
		addCourse.setPrice(map.get("Price"));
		addCourse.uploadPhoto(map.get("Photo"));
		addCourse.setDescription(map.get("Description"), web);
		addCourse.clickSave();
		
		soft.assertEquals(courseList.getSuccessMessage(), "Success");
		courseList.deleteCource(web, map.get("Name"));
		soft.assertEquals(courseList.getSuccessMessage(), "Success");
		
		if(courseList.getSuccessMessage().equals("Success"))
			excel.updateTestStatus("AddCourse", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("AddCourse", "Fail", IConstantPath.EXCEL_PATH);
		soft.assertAll();
	}

}
