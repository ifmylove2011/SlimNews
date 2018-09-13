package com.xter.slimnews.data.source.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.xter.support.util.L;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by XTER on 2017/7/3.
 * 智慧家居用
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

	private final Gson gson;
	private final TypeAdapter<T> adapter;

	public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		String json = value.string();
		L.i(json);
		try {
			return adapter.fromJson(json);
		} finally {
			value.close();
		}
//		JsonReader jsonReader = gson.newJsonReader(value.charStream());
//		try {
//			return adapter.read(jsonReader);
//		} finally {
//			value.close();
//		}
	}
}
