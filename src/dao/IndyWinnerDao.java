package dao;

import java.math.BigInteger;
import java.util.List;

import model.IndyWinnerModel;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateXmlConfig;

public class IndyWinnerDao {
	
	@SuppressWarnings("unchecked")
	public List<IndyWinnerModel> getAllIndyWinner(int offset, int noOfRecords) {

		Transaction transaction = null;
		List<IndyWinnerModel> listOfIndyWinner = null;
		String query = "select * from indywinners limit " + offset + "," + noOfRecords;
		try (Session session = HibernateXmlConfig.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get  object
			
			listOfIndyWinner = session.createNativeQuery(query).getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfIndyWinner;
	}
	
	public int countIndyWinner() {
		Transaction transaction = null;
		int countIndyWinner = 0 ;
		try (Session session = HibernateXmlConfig.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			countIndyWinner = ((BigInteger) session.createNativeQuery("select count(*) from indywinners").getSingleResult()).intValue();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return countIndyWinner;
	}

}
