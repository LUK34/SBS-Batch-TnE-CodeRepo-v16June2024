
# 1. BATCH-DS-MYSQL-ORA
- This programme will help establish a connection between MySQL and Oracle using Entity Manager.

# 2. BATCH-2DS-MYSQL
- This programme will help establish a connection with 2 different schemas present in a MySQL database using Entity Manager.

# 3. BATCH-2DS-ORA
- This programme will help establish a connection with 2 different schemas present in a Oracle database using Entity Manager.
orai18n is applied for arabic language. So this file has to be inserted in buildpath to connect to db.

# orai18n-12.1.0.2
- Add this file in the buildpath for BATCH-2DS-ORA to work

# 4. BATCH-CsvToMySql-2.x.x
- This code will work for java 1.8, Version: 2.7.6
- This file will read the file from CSV file , process the buisness logic and write the data into MySql DB.
- Dummy data present in csv file is generated from mockaroo website.
- https://www.mockaroo.com/

# 5. BATCH-CsvToMySql-3.x.x
- This code will work for java 17, Version: 3.3.1
- This file will read the file from CSV file , process the buisness logic and write the data into MySql DB.
- Dummy data present in csv file is generated from mockaroo website.
- Disable `@EnableBatchProcessing` for 3.x.x 
- https://www.mockaroo.com/

# 6. BATCH-MysqlToCsv-2.x.x
- This code will work for java 1.8, Version: 2.7.6
- This file will read the data from MySql Database having percentage >90.0 .
- The data will be read and be stored into csv file inside a folder called `filelocation`.

# 7. BATCH-MysqlToCsv-3.x.x
- This code will work for java 17, Version: 3.3.1
- This file will read the data from MySql Database having percentage >90.0 .
- The data will be read and be stored into csv file inside a folder called `filelocation`.
- Disable `@EnableBatchProcessing` for 3.x.x 