package com.anfesoft.training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.query.Query;

import com.anfesoft.commons.JournalTradesModel;
import com.anfesoft.entities.ImaginalActs;
import com.anfesoft.entities.JournalTrades;
import com.anfesoft.entities.Mudras;
import com.anfesoft.entities.TradingAccounts;
import com.anfesoft.entities.Web2pyDocumentation;
import com.anfesoft.models.TradingAccountsModel;

public class Queries {

	public static void query1() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyConnectionX");
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction();
		@SuppressWarnings("unchecked")
		Query<Mudras> createQuery = ((Query<Mudras>) em1.createQuery("select new Mudras(m.id, m.name) from Mudras m"));
		Query<Mudras> q = createQuery;
		List<Mudras> results = q.list();
		for (Mudras x : results) {
			System.out.println(x.getId());
			System.out.println(x.getName());
		}
		em1.close();
	}

	public static String[] query2() {
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("MyConnectionX");
		EntityManager em2 = emf2.createEntityManager();
		em2.getTransaction();
		@SuppressWarnings("unchecked")
		Query<Mudras> createQuery = ((Query<Mudras>) em2.createQuery("select new Mudras(m.id, m.name) from Mudras m"));
		Query<Mudras> q = createQuery;
		List<Mudras> results = q.list();
		String labels[] = {};
		for (int i = 1; i < results.size(); i++) {
			labels[i] = results.get(i).getName();
		}
		em2.close();
		return labels;
	}

	@SuppressWarnings("unchecked")
	public static List<Mudras> getAccounts() {
		List<Mudras> ledger = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<TradingAccounts> createQuery = ((Query<TradingAccounts>) em3.createQuery(
				"select new TradingAccounts(m.id, m.mailid, m.name, m.anfang, m.gewinnen, m.netgewinnen) from TradingAccounts m"));
		Query<TradingAccounts> q = createQuery;
		List<TradingAccounts> results = q.list();
		for (TradingAccounts x : results) {
			HashMap<String, String> bankDetails = new HashMap<String, String>();
			bankDetails.put("name", x.getName());
			bankDetails.put("anfang", x.getAnfang().toString());
			bankDetails.put("gewinnen", x.getGewinnen().toString());
			bankDetails.put("netgewinnen", x.getNetgewinnen().toString());
			ledger.addAll((Collection<? extends Mudras>) bankDetails);
		}
		em3.close();

		return ledger;
	}

	public static List<Map<String, Object>> getAccountsData() {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		@SuppressWarnings("unchecked")
		Query<TradingAccounts> query = (Query<TradingAccounts>) em3.createQuery(
				"select new TradingAccounts(m.id, m.mailid, m.name, m.anfang, m.gewinnen, m.netgewinnen) from TradingAccounts m");
		Query<TradingAccounts> createQuery = query;
		Query<TradingAccounts> q = createQuery;
		List<TradingAccounts> results = q.list();
		for (TradingAccounts x : results) {
			Map<String, Object> elements = new HashMap<>();
			elements.put(TradingAccountsModel.ID, x.getId());
			elements.put(TradingAccountsModel.NAME, x.getName());
			elements.put(TradingAccountsModel.MAIL, x.getMailid());
			elements.put(TradingAccountsModel.START, x.getAnfang());
			elements.put(TradingAccountsModel.WIN, x.getGewinnen());
			elements.put(TradingAccountsModel.NETWIN, x.getNetgewinnen());
			listresults.add(elements);
		}
		return listresults;
	}

	public static Object query3(int n) {
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("MyConnectionX");
		EntityManager em2 = emf2.createEntityManager();
		em2.getTransaction();
		@SuppressWarnings("unchecked")
		Query<Mudras> createQuery = ((Query<Mudras>) em2.createQuery("select new Mudras(m.id, m.name) from Mudras m"));
		Query<Mudras> q = createQuery;
		List<Mudras> results = q.list();
		Object mystring = results.get(n).getName();
		em2.close();
		return mystring;
	}

	public static void updateRow(JTable tableTradingAccounts, int selection) {
		TradingAccounts ta = new TradingAccounts();		
		EntityManagerFactory amf = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em1 = amf.createEntityManager();
		em1.getTransaction().begin();
		String updateQuery = "UPDATE TradingAccounts SET MAILID =:p_mailid, NAME = :p_name, ANFANG = :p_anfang , GEWINNEN = :p_gewinnen ,NETGEWINNEN = :p_netgewinnen WHERE ID = :p_id";
		Query query = (Query) em1.createQuery(updateQuery);
		query.setParameter("p_mailid", ta.getMailid());
		query.setParameter("p_name", tableTradingAccounts.getValueAt(selection, TradingAccountsModel.NAME_POS));
		query.setParameter("p_anfang", tableTradingAccounts.getValueAt(selection, TradingAccountsModel.START_POS));
		query.setParameter("p_gewinnen", tableTradingAccounts.getValueAt(selection, TradingAccountsModel.WIN_POS));
		query.setParameter("p_netgewinnen", tableTradingAccounts.getValueAt(selection, TradingAccountsModel.NETWIN_POS));
		query.setParameter("p_id", tableTradingAccounts.getValueAt(selection, TradingAccountsModel.ID_POS));
		int result = query.executeUpdate();
		System.out.println("Rows affected:"+result);
		em1.getTransaction().commit();
		amf.close();
	}

	public static void insertEntryJournalTrades(String text, JTable tableLog) {		
		Date date = new Date();
		EntityManagerFactory em = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em1 = em.createEntityManager();
		em1.getTransaction().begin();
		JournalTrades jt = new JournalTrades(0 , date, text);
		em1.persist(jt);
		//the model
		DefaultTableModel mymodel = (DefaultTableModel) tableLog.getModel();
		mymodel.addRow(new Object[] {date});
		
		em1.getTransaction().commit();
		em1.refresh(jt);
		em.close();
	}

	public static void insertEntryImaginalActs(String text, JTable tabel) {		
		Date date = new Date();
		EntityManagerFactory em = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em1 = em.createEntityManager();
		em1.getTransaction().begin();
		ImaginalActs jt = new ImaginalActs(0 , false, date, text);
		em1.persist(jt);
		em1.getTransaction().commit();
	    DefaultTableModel model = (DefaultTableModel) tabel.getModel();
	    model.addRow(new Object[] {text});
		em.close();
	}
	
	public static List<Map<String, Object>> getTradingLogsData() {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		@SuppressWarnings("unchecked")
		Query<JournalTrades> query = (Query<JournalTrades>) em3.createQuery(
				"select new JournalTrades(m.id, m.times, m.body) from JournalTrades m ORDER BY m.times DESC");
		Query<JournalTrades> createQuery = query;
		Query<JournalTrades> q = createQuery;
		List<JournalTrades> results = q.list();
		for (JournalTrades x : results) {
			Map<String, Object> elements = new HashMap<>();
			elements.put(JournalTradesModel.ID, x.getId());
			elements.put(JournalTradesModel.TIMES, x.getTimes());
			listresults.add(elements);
		}
		return listresults;
	}

	public static String getEntryJournalLog(Object date) {
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		@SuppressWarnings("unchecked")
		Query<JournalTrades> query = (Query<JournalTrades>) em3.createQuery(
				"select new JournalTrades(m.id, m.times, m.body) from JournalTrades m where m.times = :givenTime");
		query.setParameter("givenTime", (Date)date);
		Query<JournalTrades> createQuery = query;
		Query<JournalTrades> q = createQuery;
		List<JournalTrades> results = q.list();
		for (JournalTrades x : results) {
			return x.getBody();
		}
		return "";		
	}

	public static List<Map<String, Object>> getImaginalActsData() {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		@SuppressWarnings("unchecked")
		Query<ImaginalActs> query = (Query<ImaginalActs>) em3.createQuery(
				"select new ImaginalActs(m.id, m.done, m.times, m.body) from ImaginalActs m ORDER BY m.times DESC");
		Query<ImaginalActs> createQuery = query;
		Query<ImaginalActs> q = createQuery;
		List<ImaginalActs> results = q.list();
		for (ImaginalActs x : results) {
			Map<String, Object> elements = new HashMap<>();
			elements.put("ID", x.getId());
			elements.put("DONE", x.getDone());
			elements.put("TIMES", x.getTimes());
			elements.put("ENTRY", x.getBody());
			listresults.add(elements);
		}
		return listresults;
	}

	/*this method will filter based off searchText box input.
	 * notice that this method doesn't abide to the single resposability principle.
	 * we are searching and rebuilding the model
	 * therefore the model should be rebuilt in another method.
	 * */
	public static void getBalancesCryptoSearch(JTable tableSample, String searchText) {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		@SuppressWarnings("unchecked")
		Query<TradingAccounts> query = (Query<TradingAccounts>) em3.createQuery(
				"select new TradingAccounts(m.id, m.mailid, m.name, m.anfang, m.gewinnen, m.netgewinnen) from TradingAccounts m WHERE m.name like :p_searchText");
		Query<TradingAccounts> createQuery = query;
		Query<TradingAccounts> q = createQuery;
		q.setParameter("p_searchText", "%"+searchText+"%");
		DefaultTableModel tmodel = (DefaultTableModel) tableSample.getModel();
		tmodel.setRowCount(0);
		for (TradingAccounts x : q.list()) {
			tmodel.addRow(new Object[] {x.getId(), x.getName(), x.getAnfang(),x.getNetgewinnen(),x.getGewinnen()});
		//	listresults.add(elements);
		}
		
	}

	public static List<Map<String, Object>> listWeb2pyTitles(String searchParam) {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("web2pyCon");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<Web2pyDocumentation> query = (Query<Web2pyDocumentation>) em3.createQuery(
				"select new Web2pyDocumentation(m.id, m.body, m.tags, m.url, m.title) from Web2pyDocumentation m WHERE m.title like :p_searchText");
		query.setParameter("p_searchText", "%"+searchParam+"%");
		for(Web2pyDocumentation x: query.list())
		{
			Map<String, Object> elements = new HashMap<>();
			elements.put("ID", x.getId());
			elements.put("TITLE", x.getTitle());
			System.out.println("\n"+x.getTitle());
			listresults.add(elements);
		}
		return listresults;
	}

	public static List<Map<String, Object>> getWeb2pyTitlesData() {
		List<Map<String, Object>> listresults = new ArrayList<>();
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("web2pyCon");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<Web2pyDocumentation> query = (Query<Web2pyDocumentation>) em3.createQuery(
				"select new Web2pyDocumentation(m.id, m.body, m.tags, m.url, m.title) from Web2pyDocumentation m");
		for(Web2pyDocumentation x: query.list())
		{
			Map<String, Object> elements = new HashMap<>();
			elements.put("ID", x.getId());
			elements.put("TITLE", x.getTitle());
			listresults.add(elements);
		}
		return listresults;
	}

	public static void getWeb2pyDocumentationTitlesSearch(DefaultTableModel tabelModel, String searchText) {
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("web2pyCon");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<Web2pyDocumentation> query = (Query<Web2pyDocumentation>) em3.createQuery(
				"select new Web2pyDocumentation(m.id, m.body, m.tags, m.url, m.title) from Web2pyDocumentation m WHERE m.title like :p_text");
		query.setParameter("p_text", "%" + searchText + "%");
		DefaultTableModel tmodel = tabelModel;
		tmodel.setRowCount(0);
		for (Web2pyDocumentation x : query.list()) {
			tmodel.addRow(new Object[] { x.getTitle() });

		}
	}

	public static String getWeb2pyDocumentationGetBodyByTitle(String selectedTitle) {
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("web2pyCon");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<Web2pyDocumentation> query = (Query<Web2pyDocumentation>) em3.createQuery(
				"select new Web2pyDocumentation(m.id, m.body, m.tags, m.url, m.title) from Web2pyDocumentation m WHERE m.title like :p_text");
		query.setParameter("p_text", "%"+selectedTitle+"%");
		return query.list().get(0).getBody().toString();

	}

	public static void imaginalActUpdateDone(TableModel model, boolean b, int i, JTable tableSample) {
		ImaginalActs ta = new ImaginalActs();		
		EntityManagerFactory amf = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em1 = amf.createEntityManager();
		em1.getTransaction().begin();
		String updateQuery = "UPDATE ImaginalActs SET DONE =:p_done WHERE ID = :p_id";
		Query query = (Query) em1.createQuery(updateQuery);
		query.setParameter("p_done", b);
		query.setParameter("p_id", i);
		int result = query.executeUpdate();
		System.out.println("Rows affected:"+result);
		em1.getTransaction().commit();
		amf.close();
	}

	public static int getImaginalActsRowid(String textRow) {
		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("MyConnectionAccounts");
		EntityManager em3 = emf3.createEntityManager();
		em3.getTransaction();
		Query<ImaginalActs> query = (Query<ImaginalActs>) em3.createQuery(
				"select new ImaginalActs(m.id) from ImaginalActs m WHERE m.body like :p_text");
		query.setParameter("p_text", "%"+textRow+"%");
		return query.list().get(0).getId();
	}
}
