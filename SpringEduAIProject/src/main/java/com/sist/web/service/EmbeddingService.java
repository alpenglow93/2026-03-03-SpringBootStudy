package com.sist.web.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// 공통 기반 => model.embed(text) : 문자열을 float으로 변경

@Service
@RequiredArgsConstructor
public class EmbeddingService {
	private final EmbeddingModel model;
	
	public float[] createEmbedding(String text)
	{
		if(text==null || text.isBlank())
		{
			text = "내용 없음";
		}
		
		return model.embed(text);
	}
	
	public String toVectorString(float[] vector)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(int i =0; i < vector.length; i++)
		{
			if(i > 0)
			{
				sb.append(",");
			}
			sb.append(vector[i]);
		}
		sb.append("]");
		
		return sb.toString();
	}
}
