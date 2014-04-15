package com.udm.health.displaytag;

import org.apache.commons.lang3.time.FastDateFormat;
import org.displaytag.decorator.TableDecorator;

import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.response.ResponseStatus;

public class RequestLogDecorator extends TableDecorator {

	private static final FastDateFormat formatter = FastDateFormat
			.getInstance("yyyy-MM-dd HH:mm:ss");

	public String getReceivedDate() {
		RequestLog log = getRequestLog();
		String dateString = "";
		if (log.getCreateDate() != null) {
			dateString = formatter.format(log.getCreateDate());
		}
		return dateString;
	}

	public String getResponseCode() {
		RequestLog log = getRequestLog();
		String status = "";

		if (log.getResponseCode() != null) {
			status = String.valueOf(log.getResponseCode());
			if (ResponseStatus.SUCCESS.getCode().equals(status)) {
				status = "Success";
			} else if (ResponseStatus.FAILURE.getCode().equals(status)) {
				status = "Failure";
			}
		}

		return status;
	}

	public String getRequestXml() {
		RequestLog log = getRequestLog();
		return String.format(
				"<a href=\"javascript:displayXml('request', %s)\">View</a>",
				log.getId());
	}

	public String getResponseXml() {
		RequestLog log = getRequestLog();
		return String.format(
				"<a href=\"javascript:displayXml('response', %s)\">View</a>",
				log.getId());
	}



	// the following method are exposed for testing
	RequestLog getRequestLog() {
		return (RequestLog) getCurrentRowObject();
	}


}
