import boto3


mySession = boto3.session.Session()

s3_client = mySession.client('s3')

objs_list = s3_client.list_objects_v2(Bucket='ctatisetti-aptroid-backup')
print(objs_list['IsTruncated'])

for obj in objs_list['Contents']:
    print(obj['Key'])