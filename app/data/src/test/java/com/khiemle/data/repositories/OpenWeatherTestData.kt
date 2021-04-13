package com.khiemle.data.repositories

val saigonForecastDailyData = """
{
	"city": {
		"id": 1580578,
		"name": "Ho Chi Minh City",
		"coord": {
			"lon": 106.6667,
			"lat": 10.8333
		},
		"country": "VN",
		"population": 0,
		"timezone": 25200
	},
	"cod": "200",
	"message": 0.0633782,
	"cnt": 7,
	"list": [{
		"dt": 1618286400,
		"sunrise": 1618267398,
		"sunset": 1618311844,
		"temp": {
			"day": 34.87,
			"min": 26.34,
			"max": 36.02,
			"night": 28.72,
			"eve": 31.26,
			"morn": 26.34
		},
		"feels_like": {
			"day": 37.27,
			"night": 26.34,
			"eve": 34.4,
			"morn": 26.34
		},
		"pressure": 1012,
		"humidity": 41,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 5.9,
		"deg": 155,
		"gust": 7.38,
		"clouds": 60,
		"pop": 0.36,
		"rain": 0.6
	}, {
		"dt": 1618372800,
		"sunrise": 1618353766,
		"sunset": 1618398246,
		"temp": {
			"day": 33.75,
			"min": 26,
			"max": 34.39,
			"night": 26.83,
			"eve": 32.81,
			"morn": 26
		},
		"feels_like": {
			"day": 36.91,
			"night": 26,
			"eve": 35.64,
			"morn": 26
		},
		"pressure": 1012,
		"humidity": 47,
		"weather": [{
			"id": 501,
			"main": "Rain",
			"description": "moderate rain",
			"icon": "10d"
		}],
		"speed": 5.28,
		"deg": 150,
		"gust": 5.99,
		"clouds": 76,
		"pop": 1,
		"rain": 6.49
	}, {
		"dt": 1618459200,
		"sunrise": 1618440134,
		"sunset": 1618484649,
		"temp": {
			"day": 32.67,
			"min": 26.3,
			"max": 34.16,
			"night": 27.79,
			"eve": 33.88,
			"morn": 26.3
		},
		"feels_like": {
			"day": 35.91,
			"night": 26.3,
			"eve": 36.29,
			"morn": 26.3
		},
		"pressure": 1011,
		"humidity": 51,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 5.06,
		"deg": 164,
		"gust": 6.78,
		"clouds": 85,
		"pop": 1,
		"rain": 3.58
	}, {
		"dt": 1618545600,
		"sunrise": 1618526503,
		"sunset": 1618571052,
		"temp": {
			"day": 32.01,
			"min": 26.66,
			"max": 32.97,
			"night": 26.9,
			"eve": 31.28,
			"morn": 26.66
		},
		"feels_like": {
			"day": 35.13,
			"night": 26.66,
			"eve": 34.44,
			"morn": 26.66
		},
		"pressure": 1012,
		"humidity": 53,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 3.93,
		"deg": 142,
		"gust": 6.99,
		"clouds": 57,
		"pop": 0.92,
		"rain": 6.98
	}, {
		"dt": 1618632000,
		"sunrise": 1618612872,
		"sunset": 1618657455,
		"temp": {
			"day": 30.86,
			"min": 25.42,
			"max": 34.03,
			"night": 27.08,
			"eve": 32.37,
			"morn": 25.42
		},
		"feels_like": {
			"day": 32.58,
			"night": 26.18,
			"eve": 34.08,
			"morn": 26.18
		},
		"pressure": 1013,
		"humidity": 51,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 5.38,
		"deg": 178,
		"gust": 7.27,
		"clouds": 99,
		"pop": 0.9,
		"rain": 4.17
	}, {
		"dt": 1618718400,
		"sunrise": 1618699241,
		"sunset": 1618743859,
		"temp": {
			"day": 31.82,
			"min": 26.56,
			"max": 35.77,
			"night": 27.94,
			"eve": 32.86,
			"morn": 26.56
		},
		"feels_like": {
			"day": 33.81,
			"night": 26.56,
			"eve": 34.95,
			"morn": 26.56
		},
		"pressure": 1013,
		"humidity": 49,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 5.18,
		"deg": 152,
		"gust": 6.36,
		"clouds": 61,
		"pop": 0.36,
		"rain": 0.29
	}, {
		"dt": 1618804800,
		"sunrise": 1618785612,
		"sunset": 1618830262,
		"temp": {
			"day": 31.98,
			"min": 26.44,
			"max": 35.14,
			"night": 27.98,
			"eve": 31.49,
			"morn": 26.44
		},
		"feels_like": {
			"day": 34.33,
			"night": 26.44,
			"eve": 33.9,
			"morn": 26.44
		},
		"pressure": 1012,
		"humidity": 50,
		"weather": [{
			"id": 500,
			"main": "Rain",
			"description": "light rain",
			"icon": "10d"
		}],
		"speed": 3.75,
		"deg": 168,
		"gust": 5.61,
		"clouds": 100,
		"pop": 0.64,
		"rain": 2.93
	}]
}
""".trimIndent()