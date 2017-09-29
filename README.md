# PRMS_Backend
SAD CA FT06 Backend

REST API for create,modify, delete and copy (to be test)

sample PUT request for create schedule:
url: http://127.0.0.1:8080/phoenix/rest/programslot/create

{ 
	"dateofProgram":"2011-07-14T02:00:00",
	"duration":"02:00:00",
	"startTime":"2011-07-14T03:00:00",
	"programName":"test"
}

sample GET Request for retrieve all schedule:
url: http://127.0.0.1:8080/phoenix/rest/programslot/all

output:
{
    "psList": [
        {
            "dateofProgram": "2011-07-14T02:00:00+08:00",
            "duration": "02:00:00+07:30",
            "programName": "test",
            "startTime": "2011-07-14T03:00:00+08:00"
        }
    ]
}


NOTE: need to check on datetime format and also about the presenter/producer details
