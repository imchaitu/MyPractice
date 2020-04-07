import snowflake.connector


cntx = snowflake.connector.connect(user="chaitu", password="He!!osnoflake@123", account="ok01283.us-east-2.aws")

crs = cntx.cursor()

crs.execute("SELECT current_version()")
row = crs.fetchone()
print(row[0])

crs.close()
cntx.close()