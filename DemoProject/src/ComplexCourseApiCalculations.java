import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexCourseApiCalculations {
	Logger logger = Logger.getLogger("ComplexCourseApiCalculations");
	@Test(priority=0)
	public void CourseAPIoperns() {
		//mocking CoursePrice api response to validate results
		JsonPath js=ReUsableMethods.rawToJson(payload.CoursePrice());
		//Ques 1: No of courses
		
		int size=js.getInt("courses.size()");
		//Ques 2: Print No of courses returned by API
		//System.out.println(size);
		logger.info(""+size);
		
		//Ques 3: Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount"); 
		//System.out.println(purchaseAmount);
		logger.info(""+purchaseAmount);

		//Ques 4: Print Title of the first course
		String TitleFirstCourse = js.getString("courses[0].title");
		//System.out.println(TitleFirstCourse);
		logger.info(""+TitleFirstCourse);
		//Ques 5: Print All course titles and their respective Prices
		for(int i=0;i<size;i++)
		{
			String courseTitle = js.getString("courses["+i+"].title");
			int coursePrice = js.getInt("courses["+i+"].price");
			
		//	System.out.println(courseTitle+"\t\t\t\t"+coursePrice);
			logger.info(courseTitle+"\t\t\t\t"+coursePrice);
		}
		
		//Ques 6: Print no of copies sold by RPA Course
		for(int i=0;i<size;i++)
		{
			String courseTitle = js.getString("courses["+i+"].title");
			if (courseTitle.equals("RPA"))
					{
						int countOfRPACopies= js.getInt("courses["+i+"].copies");
						//System.out.println(countOfRPACopies);
						logger.info(""+countOfRPACopies);
					}
		}
		
		//Ques 7: Validating sum of all courses combined is equal to purchaseAmount
		int total =0;
		for(int i=0;i<size;i++)
		{
			int coursePrice = js.getInt("courses["+i+"].price");
			int countOfRPACopies= js.getInt("courses["+i+"].copies");
			
			total = total+(coursePrice*countOfRPACopies);
		}
		
		System.out.println(total);
		System.out.println(purchaseAmount);
		Assert.assertEquals(total, purchaseAmount);
	}
	
}
