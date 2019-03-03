package com.yunforge.core.web.view;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView {
	public static final String DEFAULT_JSON_CONTENT_TYPE = "image/*";

	@SuppressWarnings("rawtypes")
	@Override
	public void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Assert.notNull(model);
		for (Iterator<?> localIterator = model.keySet().iterator(); localIterator
				.hasNext();) {
			Object o = localIterator.next();
			Object obj = model.get(o);
			if ((obj != null) && ((obj instanceof InputStream))) {
				InputStream is = (InputStream) obj;
				int i = is.available();
				byte[] data = new byte[i];
				is.read(data);
				is.close();
				response.setContentType("image/*");
				ServletOutputStream out = response.getOutputStream();
				out.write(data);
				out.close();
				break;
			}
		}
	}
}