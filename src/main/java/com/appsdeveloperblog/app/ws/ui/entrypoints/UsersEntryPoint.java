package com.appsdeveloperblog.app.ws.ui.entrypoints;


import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.appsdeveloperblog.app.ws.annotations.Secured;
import com.appsdeveloperblog.app.ws.service.UsersService;
import com.appsdeveloperblog.app.ws.service.impl.UsersServiceImpl;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.request.CreateUserRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserProfileModel;
import com.appsdeveloperblog.app.ws.ui.model.response.DeleteUserProfileResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.RequestOperation;
import com.appsdeveloperblog.app.ws.ui.model.response.ResponseStatus;
import com.appsdeveloperblog.app.ws.ui.model.response.UserProfileRest;


@Path("/users")
public class UsersEntryPoint {
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML} )
	public UserProfileRest createUser(CreateUserRequestModel requestObject) {
		UserProfileRest response = new UserProfileRest();
		
		// Prepare UserDTO
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(requestObject, userDto);
		
		// Create new user
		UsersService userService = new UsersServiceImpl();
		UserDTO createdUserProfile = userService.createUser(userDto); 
		
		// Prepare response
		BeanUtils.copyProperties(createdUserProfile, response);
		
		return response;
	}
	

	@Secured
	@GET
	@Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML} )
	public UserProfileRest getUserProfile(@PathParam("id") String id) {

		UserProfileRest returnValue = null;
		UsersService userService = new UsersServiceImpl();
		UserDTO userProfile = userService.getUser(id);
		
		
		returnValue = new UserProfileRest();
		BeanUtils.copyProperties(userProfile, returnValue);
		
		return returnValue;
		
		
	}
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML} )
	public List<UserProfileRest> getUsers(@DefaultValue("0") @QueryParam("start") int start, 
			@DefaultValue("50") @QueryParam("limit") int limit) {
		
		UsersService userService = new UsersServiceImpl();
		List<UserDTO> users = userService.getUsers(start, limit);
		
		// Prepare return value
		List<UserProfileRest> returnValue = new ArrayList<UserProfileRest>();
		for(UserDTO userDto: users) {
			UserProfileRest userModel = new UserProfileRest();
			BeanUtils.copyProperties(userDto, userModel);
			userModel.setHref("/users/" + userDto.getUserId());
			returnValue.add(userModel);
			
		}
		
		return returnValue;
		
	}
	
	@Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest updateUserProfile(@PathParam("id") String id,
            UpdateUserProfileModel userDetails) {

		UsersService userService = new UsersServiceImpl();
        UserDTO storedUserDetails = userService.getUser(id);
        
        if(userDetails.getFirstName() !=null && !userDetails.getFirstName().isEmpty())
        {
            storedUserDetails.setFirstName(userDetails.getFirstName());  
        }
        if(userDetails.getLastName() != null && !userDetails.getLastName().isEmpty()) {
			storedUserDetails.setLastName(userDetails.getLastName());
		}
        
        // Update User Details
        userService.updateUserDetails(storedUserDetails);
        
        // Prepare return value 
        UserProfileRest returnValue = new UserProfileRest();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
	}
	
	@Secured
	@DELETE
	@Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeleteUserProfileResponseModel deleteUserProfile(@PathParam("id") String id) {
		
		DeleteUserProfileResponseModel returnValue = new DeleteUserProfileResponseModel();
		returnValue.setRequestOperation(RequestOperation.DELETE);
		
		UsersService userService = new UsersServiceImpl();
		UserDTO storedUserDetails = userService.getUser(id);
		
		userService.deleteUser(storedUserDetails);
		
		returnValue.setResponseStatus(ResponseStatus.SUCCESS);
		
		return returnValue;
	}
	
}
