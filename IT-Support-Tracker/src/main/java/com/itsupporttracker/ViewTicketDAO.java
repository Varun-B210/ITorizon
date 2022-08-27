package com.itsupporttracker;

import java.sql.ResultSet;	
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itsupporttracker.exception.NotValidException;
import com.itsupporttracker.exception.UserNotFoundException;
import com.itsupporttracker.repository.CommentRepository;
import com.itsupporttracker.repository.TicketRepository;


@Repository
public class ViewTicketDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	TicketRepository ticketRepository;
	
	class TicketMapper implements RowMapper<Viewticket>{

		@Override
		public Viewticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Viewticket viewticket = new Viewticket();
		viewticket.setTicketId(rs.getInt("ticket_Id"));
		viewticket.setCategoryDesc(rs.getString("category_Desc"));
		viewticket.setSubCategoryDesc(rs.getString("sub_Category_Desc"));
		viewticket.setSubject(rs.getString("subject"));
		viewticket.setStatus(rs.getString("status"));
		viewticket.setPriority(rs.getString("priority"));
		viewticket.setAssignee(rs.getString("assignee"));
		String link = "http://localhost:8080/user/viewByID?ticketId="+viewticket.getTicketId();
		viewticket.setLink(link);
		viewticket.setComment(commentRepository.findByTicketId(viewticket.getTicketId()));
		
			return viewticket;
		}
	}
	public List<Viewticket> viewAllTickets(){
		return jdbcTemplate.query("select t.ticket_id, c.category_Desc, sc.sub_category_Desc, t.subject, p.priority, s.status, a.assignee from ticket t, category c, sub_category sc, priority p, status s, assignee a\r\n"
				+ "where t.category_id = c.category_Id and t.sub_Category_Id=sc.sub_Category_Id and t.priority_id=p.priority_Id and t.status_id=s.status_Id and t.assignee_Id=a.assignee_Id"
				, new TicketMapper());
	}
	
	public Viewticket findByID(String ticketId) throws UserNotFoundException {
		try {
			 if(Integer.valueOf(ticketId)!=Integer.valueOf(ticketRepository.findByTicketId(Integer.valueOf(ticketId)).getTicketId()))
			{
				 throw new NotValidException("Not valid input");
			 }
			 else {
				Viewticket findByID =  jdbcTemplate.queryForObject("select t.ticket_id, c.category_Desc, sc.sub_category_Desc, t.subject, p.priority, s.status, a.assignee from ticket t, category c, sub_category sc, priority p, status s, assignee a\r\n"
				+ "where t.category_id = c.category_Id and t.sub_Category_Id=sc.sub_Category_Id and t.priority_id=p.priority_Id and t.status_id=s.status_Id and t.assignee_Id=a.assignee_Id and ticket_id =?", new Object[] { ticketId },
				new BeanPropertyRowMapper<Viewticket>(Viewticket.class));
		return findByID;
			 }
		}catch (Exception e) 
		{
			throw new UserNotFoundException("Ticket not found");
		}
	}
	
}
