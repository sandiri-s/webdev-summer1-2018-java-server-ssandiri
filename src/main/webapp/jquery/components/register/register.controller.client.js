(function () {
	var $usernameFld, $passwordFld, $verifyPasswordFld;
	var $registerBtn;
	var userService = new UserServiceClient();
	$(main);

	function main() {
		$('#signup-btn').click(register);

	}
	function register() { 

		$usernameFld = $('#username').val();
		$passwordFld = $('#password').val();
		var user = new User();
		user.setUsername($usernameFld);
		user.setPassword($passwordFld);
		userService
		.register(user).then((res) => {
			if(res.status=200){
				res.json().then((resUser) => { sessionStorage.setItem("userID",resUser.id);
				window.location.href = '../profile/profile.template.client.html';
				})
				
				
			
			}
			else if(res.status ==409)
			{
				alert("The username is already taken, please choose a new username");

			}
			else 
			{
				alert("signup unsuccessful, http error staus:" + res.status);

			}});
	}
})();