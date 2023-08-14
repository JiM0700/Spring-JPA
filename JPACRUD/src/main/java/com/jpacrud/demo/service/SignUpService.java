package com.jpacrud.demo.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpacrud.demo.model.Response;
import com.jpacrud.demo.model.SignUpModel;
import com.jpacrud.demo.repository.SignUpRepository;

@Service
public class SignUpService {

	@PersistenceContext
	private EntityManager entityManager;

	Response res = new Response();

	@Autowired
	private SignUpRepository repo;

	public Response createUser(SignUpModel values) {

		try {

			String uuid = UUID.randomUUID().toString();
			values.setsNo(uuid);
			values.setCreatedBy(uuid);
			values.setUpdatedBy(uuid);

			Date date = new Date(Calendar.getInstance().getTime().getTime());
			values.setCreatedDate(date);
			values.setUpdatedDate(date);

			values.setActive(true);

			repo.save(values);

			res.setData("User Created Successfully");
			res.setResponseCode(200);
			res.setResponseMessage("Success");

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Cannot Create User");
			res.setResponseCode(500);
			res.setResponseMessage("Error");

		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getAllUser() {

		try {

			List<SignUpModel> list = repo.findAll();

			JSONArray jsonArray = new JSONArray();

			for (SignUpModel values : list) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sNo", values.getsNo());
				jsonObject.put("firstName", values.getFirstName());
				jsonObject.put("lastName", values.getLastName());
				jsonObject.put("gender", values.getGender());
				jsonObject.put("dob", values.getDob());
				jsonObject.put("mobileNumber", values.getMobileNumber());
				jsonObject.put("emailId", values.getEmailId());
				jsonObject.put("password", values.getPassword());
				jsonObject.put("createdBy", values.getCreatedBy());
				jsonObject.put("updatedBy", values.getUpdatedBy());
				jsonObject.put("createdDate", values.getCreatedDate());
				jsonObject.put("updatedDate", values.getUpdatedDate());

				jsonArray.add(jsonObject);

			}

			res.setjData(jsonArray);
			res.setData("User Retrieved Successfully");
			res.setResponseCode(200);
			res.setResponseMessage("Success");

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Unable to Retrieve User");
			res.setResponseCode(500);
			res.setResponseMessage("Error");
		}

		return res;
	}

	public Response updateEmail(SignUpModel values) {

		try {

			Optional<SignUpModel> optObj = repo.findById(values.getsNo());

			if (optObj.isPresent()) {

				SignUpModel updateEmail = optObj.get();
				updateEmail.setEmailId(values.getEmailId());

				repo.save(updateEmail);

				res.setData("Email Updated");
				res.setResponseCode(200);
				res.setResponseMessage("Success");

			} else {
				res.setData("Cannot Update Email");
				res.setResponseCode(500);
				res.setResponseMessage("Error");

			}

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Cannot Perform Operation");
			res.setResponseCode(500);
			res.setResponseMessage("Error");
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getOneUser(SignUpModel values) {

		try {

			Optional<SignUpModel> list = repo.findById(values.getsNo());

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("sNo", values.getsNo());
			jsonObject.put("firstName", values.getFirstName());

			res.setjData(jsonObject);
			res.setData("User Retrieved Successfully");
			res.setResponseCode(200);
			res.setResponseMessage("Success");

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Unable to Retrieve User");
			res.setResponseCode(500);
			res.setResponseMessage("Error");
		}

		return res;
	}

	public Response deleteUser(String sNo) {

		try {

			repo.deleteById(sNo);

			res.setData("User Deleted Successfully");
			res.setResponseCode(200);
			res.setResponseMessage("Success");

		} catch (Exception e) {

			res.setData("Failed to Delete user");
			res.setResponseCode(500);
			res.setResponseMessage("Error");

		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response userLogin(String emailId, String password) {

		System.out.println("Entering Method");

		try {

			System.out.println("Entering try");

			Query query = entityManager
					.createQuery("SELECT u FROM SignUpModel u  WHERE u.emailId = :emailId AND u.password = :password");

			query.setParameter("emailId", emailId);
			query.setParameter("password", password);

			System.out.println(query.toString());

			System.out.println(query.getResultList().toString());

			List<SignUpModel> value = query.getResultList();

			String result;

			if (value.isEmpty()) {

				System.out.println("Entering If");
				result = "No Such User Found!";

			} else {

				System.out.println("Entering else");
				result = "Existing User!";

			}

			res.setData(result);
			res.setResponseCode(200);
			res.setResponseMessage("Success");

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Login Failed");
			res.setResponseCode(500);
			res.setResponseMessage("Error");

		}

		return res;
	}

	public Response userInactive(String sNo) {

		try {

			Optional<SignUpModel> optObj = repo.findById(sNo);

			if (optObj.isPresent()) {

				SignUpModel fakedelete = optObj.get();
				fakedelete.setActive(false);

				repo.save(fakedelete);

				res.setData("Account Deleted");
				res.setResponseCode(200);
				res.setResponseMessage("Success");

			} else {
				res.setData("Unable to Delete Account");
				res.setResponseCode(500);
				res.setResponseMessage("Error");

			}

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Cannot Perform Operation");
			res.setResponseCode(500);
			res.setResponseMessage("Error");
		}

		return res;
	}

}
