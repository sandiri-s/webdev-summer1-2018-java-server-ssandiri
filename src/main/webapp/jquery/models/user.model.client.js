function User(username, password, firstName, lastName, phone, email, role, dateOfBirth){
  this.username = username;
  this.password = password;
  this.firstName = firstName
  this.lastName = lastName
  this.phone = phone
  this.email = email
  this.role = role
  this.dateOfBirth = dateOfBirth
  
  this.setUsername = setUsername;
  this.getUsername = getUsername;
  this.setPassword = setPassword;
  this.getPassword = getPassword;
  this.setFirstName = setFirstName;
  this.getFirstName = getFirstName;
  this.setPhone = setPhone;
  this.getPhone = getPhone;
  this.setEmail = setEmail;
  this.getEmail = getEmail;
  this.setRole = setRole;
  this.getRole = getRole;
  this.setDateOfBirth = setDateOfBirth
  this.getDateOfBirth = getDateOfBirth
  

  function setUsername(username) {
	  this.username = username;
  }
  function getUsername() {
	  return this.username;
  }

  function setPassword(password) {
	  this.Password = password;
  }
  function getPassword() {
	  return this.Password;
  }
  
  function setFirstName(firstName) {
	  this.firstName = firstName;
  }
  function getFirstName() {
	  return this.firstName;
  }
  
  function setPhone(phone) {
	  this.phone =phone;
  }
  function getPhone() {
	  return this.phone;
  }
  
  function setEmail(email) {
	  this.email = email;
  }
  function getEmail() {
	  return this.email;
  }
  
  function setRole(role) {
	  this.role = role;
  }
  function getRole() {
	  return this.role;
  }
  
  function setDateOfBirth(dateOfBirth) {
	  this.dateOfBirth = dateOfBirth;
  }
  function getDateOfBirth() {
	  return this.dateOfBirth;
  }

  
}