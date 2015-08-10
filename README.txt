Prerequisites:

Apache ActiveMQ must be installed.
The ApacheMQ broker should be running on its default port of 61616

MySQL/MariaSQL must be installed and running on its default port. A user for the app should be created.


Building:

Gradle jobs should be run with the following parameters: -PdbUsername=<username> -PdbPassword=<password>. These properties are used by Flyway to perform DB migrations.