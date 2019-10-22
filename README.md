# SalonKeeper
This Java application was a windows desktop-based application that was designed and built to kept track of the day to day dealing of a salon style business. This used a MySQL database for information storage and retrieval. 
<hr>
<div>
  <h2>Information</h2>
      This application has the ability in different areas :
      <ul> <h3>Employees</h3>
        <ul><h4>Create New Employees</h4>
          <li>Employee Information</li>
          <li>Employee Active Status</li>
          <li>Employee Password</li>
          <li>Customize the services offered for each employee</li>
        </ul>
      </ul>
      <ul><h3>Clients</h3>
        <ul><h4>Create New Clients</h4>
          <li>Enter basic client information</li>
          <li>Enter client notes for previous services</li>
          <li>Enter client appointment information</li>
        </ul>
      </ul>
      <ul><h3>Appointments</h3>
        <ul><h4>Create New Appointments</h4>
          <li>Upon login, view the scheduled appointments for the day</li>
          <li>Create new appointments with client, stylist, and multiple services</li>
          <li>Edit appointment information</li>
          <li>Delete appointments</li>
        </ul>
      </ul>
      <ul><h3>Reports</h3>
        <ul><h4>Employee Reporting</h4>
        <li>View the client retention percentages within a given time frame</li>
          <li>View the number of certain types of services rendered by the logged in employee within a time frame</li>
          <li>View appointment history within a given time frame</li>
        </ul>
        <ul><h4>Manager Reporting</h4>
          <li>View the client retention percentages within a given time frame for each stylist</li>
          <li>View the number of certain types of services rendered by the each employee within a time frame</li>
          <li>View appointment history within a given time frame for any selected employee</li>
        </ul>
      </ui>
</div>
<hr>
<div>
  <h2>Usage</h2>
  <p><h5>Database ERD</h5>
    The image file in the root directory is a map of the database ERD to get an idea of how the database is structured.
  </p>
  <p><h5>Database Creation</h5>
    There is a script file created by My SQL Workbench which will automatically create the database and implement the structure that is     used by the application. The first user to be added to the database will be the user with administrator privileges and their       employee ID should be '1'
  </p>
  <p><h5>Logging in</h5>
  Upon launch, the user will be asked to enter the I.P. address of the database. The port number is hardcoded at the default 3306 that most databases are initially configured to use. The next line is the username of the <strong>database</strong> and not the application. The next line is the password of the <strong>database</strong> and will not be in hidden characters. If the login is incorrect or the database is not connecting, the user will be presented with an error message. Otherwise, a successful login attempt will bring the user to the login screen where the employees can now login to the system using their credentials. 
  <br><br>
  <strong>Note: </strong> Even though the user may be successfully logged in, unless the initial database has been created and set up, the rest of the application will not function properly.
  </p>
</div>
<img href="salonkeeperER.png"></img>
