package com.anfesoft.commons;

import java.util.List;
import java.util.Map;

import com.anfesoft.models.TradingAccountsModel;
import com.anfesoft.training.Queries;

public class FormatHelpers {

	/* returns a list of labels */
	public static String[] makeSimpleArray(List<Map<String, Object>> accountsData) {
		String[] labels = new String[accountsData.size()];
		int counter = 0;
		for(Map<String, Object> x: accountsData)
		{
			labels[counter] = x.get(TradingAccountsModel.NAME).toString();
			counter++;
		}
		return labels;
	}

	public static Object[][] getTableTradingAccounts(String[] columns) {
		List<Map<String, Object>> dbData = Queries.getAccountsData();
		Object[][] data = new Object[dbData.size()][columns.length];
		int rows = 0;
		for(Map<String, Object> x: Queries.getAccountsData())
		{
		data[rows][0] = x.get(TradingAccountsModel.ID);
		data[rows][1] = x.get(TradingAccountsModel.NAME);
		data[rows][2] = x.get(TradingAccountsModel.START);
		data[rows][3] = x.get(TradingAccountsModel.NETWIN);
		data[rows][4] = x.get(TradingAccountsModel.WIN);
		++rows;
		}
		return data;
	}
	
	public static Object[][] getTradingLogs(String[] columns) {
		List<Map<String, Object>> dbData = Queries.getTradingLogsData();
		Object[][] data = new Object[dbData.size()][columns.length];
		int rows = 0;
		for(Map<String, Object> x: Queries.getTradingLogsData())
		{
		data[rows][0] = x.get(JournalTradesModel.TIMES);
		++rows;
		}
		return data;
	}

	public static Object[][] getTableImaginalActs(String[] columns) {
		List<Map<String, Object>> dbData = Queries.getImaginalActsData();
		Object[][] data = new Object[dbData.size()][columns.length];
		int rows = 0;
		for(Map<String, Object> x: Queries.getImaginalActsData())
		{
		data[rows][0] = x.get("DONE");
		data[rows][1] = x.get("ENTRY");
		++rows;
		}
		return data;
	}

	public static Object[][] getWeb2pyDocumentationTitles(String[] columnsSets) {
		List<Map<String, Object>> dbData = Queries.getWeb2pyTitlesData();
		Object[][] data = new Object[dbData.size()][columnsSets.length];
		int rows = 0;
		for(Map<String, Object> x: dbData)
		{
			data[rows][0] = x.get("TITLE");
			++rows;
		}
		return data;
	}

}
