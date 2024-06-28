package kw.kng.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import kw.kng.model.ExamResult;

@Component
public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> 
{
	
	@Override
	public ExamResult process(ExamResult item) throws Exception
	{
	
		if(item.getPercentage() >= 90.0f)
		{
			System.out.println("Exam Result:: " +item);
			return item;
		}
		return null;
	}

}
