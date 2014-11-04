#!C:/Python34/python.exe -u

import cgi, cgitb
import json
from ast import literal_eval


form = cgi.FieldStorage()

data = {
    "username":form.getvalue("username"),
    "area":form.getvalue("area"),
    "problemType":form.getvalue("problemType"),
    "caseDescription":form.getvalue("caseDescription"),
    }


temp = open("test.json","r").readlines();
jsonlist = ""
for i in temp :
    jsonlist = jsonlist + i.split("\n")[0];

datalist = literal_eval(jsonlist);

open("test.json","w").writelines("[\n");
for i in datalist:
    open("test.json","a").writelines(str(i)+",\n");
open("test.json","a").writelines(str(data)+"\n");
open("test.json","a").writelines("]\n");


print("Content-Type : application/json\n")
print(json.dumps({"Error":"false"}))


#open("test.json","w").writelines("work");

#print("Content-type: text/html")

