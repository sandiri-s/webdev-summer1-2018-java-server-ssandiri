
(function () {
	var $usernameFld, $passwordFld;
	var presentUser;
	var presentUserid;
	var userService = new UserServiceClient();
	$(main);

	function main() { 
		$("#profile-update-btn").click(update);
		var id = sessionStorage.getItem("userID");
		presentUserid = id;
		userService.findUserById(id).then(fillForm)
	}
	function fillForm(user) {
		presentUser = user
		if(!user.dateOfBirth)
			{
			formattedDOB = null;
			}
		else 
			{
			var formattedDOB = user.dateOfBirth.substring(0, user.dateOfBirth.indexOf('T'));
			}
			
		$('#usernameFld').val(user.username);
		$( "#usernameFld" ).prop( "disabled", true );
		$('#phoneFld').val(user.phone);
		$('#emailFld').val(user.email);
		$('#roleFld').val(user.role);
		$('#dateOfBirthFld').val(formattedDOB);
	}
	
	function update()
	{	
		var newUser = new User();
		newUser.setUsername(presentUser.username);
		newUser.setFirstName(presentUser.firstName);
		newUser.setLastName(presentUser.lastName);
		newUser.setRole(presentUser.role);
		newUser.setPassword(presentUser.password);
		newUser.setEmail($('#emailFld').val());
		newUser.setPhone($('#phoneFld').val());
		newUser.setRole($('#roleFld').val());
		newUser.setDateOfBirth($('#dateOfBirthFld').val());
		console.log(newUser)
		userService.updateUser(presentUserid, newUser).then((res) => {
			$('#alertDiv').empty();
			$('#alertDiv').removeClass();
			$('#alertDiv').addClass("alert alert-success alert-dismissible");
			$('#alertDiv').append("Profile updated successfully");
				
			}); 

	}
})();