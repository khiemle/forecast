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
    	"message": 4.0740187,
    	"cnt": 7,
    	"list": [{
    		"dt": 1618286400,
    		"sunrise": 1618267398,
    		"sunset": 1618311844,
    		"temp": {
    			"day": 308.02,
    			"min": 299.49,
    			"max": 309.17,
    			"night": 301.59,
    			"eve": 303.96,
    			"morn": 299.49
    		},
    		"feels_like": {
    			"day": 310.42,
    			"night": 299.49,
    			"eve": 307.6,
    			"morn": 299.49
    		},
    		"pressure": 1012,
    		"humidity": 41,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 3.04,
    		"deg": 109,
    		"clouds": 60,
    		"pop": 0.36,
    		"rain": 0.6
    	}, {
    		"dt": 1618372800,
    		"sunrise": 1618353766,
    		"sunset": 1618398246,
    		"temp": {
    			"day": 306.9,
    			"min": 299.15,
    			"max": 307.54,
    			"night": 299.98,
    			"eve": 305.96,
    			"morn": 299.15
    		},
    		"feels_like": {
    			"day": 310.06,
    			"night": 299.15,
    			"eve": 308.79,
    			"morn": 299.15
    		},
    		"pressure": 1012,
    		"humidity": 47,
    		"weather": [{
    			"id": 501,
    			"main": "Rain",
    			"description": "moderate rain",
    			"icon": "10d"
    		}],
    		"speed": 2.96,
    		"deg": 165,
    		"clouds": 76,
    		"pop": 1,
    		"rain": 6.49
    	}, {
    		"dt": 1618459200,
    		"sunrise": 1618440134,
    		"sunset": 1618484649,
    		"temp": {
    			"day": 305.82,
    			"min": 299.45,
    			"max": 307.31,
    			"night": 300.94,
    			"eve": 307.03,
    			"morn": 299.45
    		},
    		"feels_like": {
    			"day": 309.06,
    			"night": 299.45,
    			"eve": 309.44,
    			"morn": 299.45
    		},
    		"pressure": 1011,
    		"humidity": 51,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 3.08,
    		"deg": 166,
    		"clouds": 85,
    		"pop": 1,
    		"rain": 3.58
    	}, {
    		"dt": 1618545600,
    		"sunrise": 1618526503,
    		"sunset": 1618571052,
    		"temp": {
    			"day": 305.16,
    			"min": 299.81,
    			"max": 306.12,
    			"night": 300.05,
    			"eve": 304.43,
    			"morn": 299.81
    		},
    		"feels_like": {
    			"day": 308.28,
    			"night": 299.81,
    			"eve": 307.59,
    			"morn": 299.81
    		},
    		"pressure": 1012,
    		"humidity": 53,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 3.2,
    		"deg": 160,
    		"clouds": 57,
    		"pop": 0.92,
    		"rain": 6.98
    	}, {
    		"dt": 1618632000,
    		"sunrise": 1618612872,
    		"sunset": 1618657455,
    		"temp": {
    			"day": 304.01,
    			"min": 298.57,
    			"max": 307.18,
    			"night": 300.23,
    			"eve": 305.52,
    			"morn": 298.57
    		},
    		"feels_like": {
    			"day": 305.73,
    			"night": 299.33,
    			"eve": 307.23,
    			"morn": 299.33
    		},
    		"pressure": 1013,
    		"humidity": 51,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 3.23,
    		"deg": 132,
    		"clouds": 99,
    		"pop": 0.9,
    		"rain": 4.17
    	}, {
    		"dt": 1618718400,
    		"sunrise": 1618699241,
    		"sunset": 1618743859,
    		"temp": {
    			"day": 304.97,
    			"min": 299.71,
    			"max": 308.92,
    			"night": 301.09,
    			"eve": 306.01,
    			"morn": 299.71
    		},
    		"feels_like": {
    			"day": 306.96,
    			"night": 299.71,
    			"eve": 308.1,
    			"morn": 299.71
    		},
    		"pressure": 1013,
    		"humidity": 49,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 1.47,
    		"deg": 131,
    		"clouds": 61,
    		"pop": 0.36,
    		"rain": 0.29
    	}, {
    		"dt": 1618804800,
    		"sunrise": 1618785612,
    		"sunset": 1618830262,
    		"temp": {
    			"day": 305.13,
    			"min": 299.59,
    			"max": 308.29,
    			"night": 301.13,
    			"eve": 304.64,
    			"morn": 299.59
    		},
    		"feels_like": {
    			"day": 307.48,
    			"night": 299.59,
    			"eve": 307.05,
    			"morn": 299.59
    		},
    		"pressure": 1012,
    		"humidity": 50,
    		"weather": [{
    			"id": 500,
    			"main": "Rain",
    			"description": "light rain",
    			"icon": "10d"
    		}],
    		"speed": 2.62,
    		"deg": 233,
    		"clouds": 100,
    		"pop": 0.64,
    		"rain": 2.93
    	}]
    }
""".trimIndent()