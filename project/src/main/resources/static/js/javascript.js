let weather = {
    apiKey: "7af5d7cd1e11fdb585f994cf20027309",
    fetchWeather: function (city) {
      fetch(
        "https://api.openweathermap.org/data/2.5/forecast?q=" +
          city +
          "&units=metric&appid=" +
          this.apiKey
      )
        .then((response) => {
          if (!response.ok) {
            alert("There is no such a city");
            throw new Error("There is no such a city");
          }
          return response.json();
        })
        .then((data) => this.displayWeather(data) 
        & this.displayWeather1(data) 
        & this.displayWeather2(data) 
        & this.displayWeather3(data) 
        & this.displayWeather4(data) 
        & this.displayWeather5(data));
    },
    displayWeather: function (data) {
      const { name, country } = data.city;
      const { icon, description } = data.list[0].weather[0];
      const { temp, humidity } = data.list[0].main;
      const { speed } = data.list[0].wind;
      const {lon, lat} = data.city.coord;
      const {dt_txt} = data.list[0];


      document.querySelector(".city").innerText = "Weather in " + name + ", " + country;
      document.querySelector(".icon").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".description").innerText = description[0].toUpperCase() + description.slice(1);
      document.querySelector(".temp").innerText = temp  + "°C";
      document.querySelector(".humidity").innerText = "Humidity: " + humidity + "%";
      document.querySelector(".wind").innerText = "Wind speed: " + speed + " km/h";
      document.querySelector(".lon").innerText = "Longtitude: " + lon;
      document.querySelector(".lat").innerText = "Latitude: " + lat;
      
    },

    displayWeather1: function (data) {
      const { temp} = data.list[7].main;
      const {dt_txt} = data.list[7];
      const { icon} = data.list[7].weather[0];

      document.querySelector(".first").innerText = temp + "°C";
      document.querySelector(".icon1").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".date1").innerText = dt_txt.slice(0, -8);

      var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      var now = new Date(dt_txt);
      document.querySelector(".bir").innerText = days[now.getDay()];
    },

    displayWeather2: function (data) {
      const { temp} = data.list[15].main;
      const {dt_txt} = data.list[15];
      const { icon} = data.list[15].weather[0];

      document.querySelector(".second").innerText = temp + "°C";
      document.querySelector(".icon2").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".date2").innerText = dt_txt.slice(0, -8);

      var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      var now = new Date(dt_txt);
      document.querySelector(".eki").innerText = days[now.getDay()];
    },

    displayWeather3: function (data) {
      const { temp} = data.list[23].main;
      const {dt_txt} = data.list[23];
      const { icon} = data.list[23].weather[0];

      document.querySelector(".third").innerText = temp + "°C";
      document.querySelector(".icon3").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".date3").innerText = dt_txt.slice(0, -8);

      var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      var now = new Date(dt_txt);
      document.querySelector(".uch").innerText = days[now.getDay()];
    },

    displayWeather4: function (data) {
      const { temp} = data.list[31].main;
      const {dt_txt} = data.list[31];
      const { icon} = data.list[31].weather[0];

      document.querySelector(".fourth").innerText = temp + "°C";
      document.querySelector(".icon4").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".date4").innerText = dt_txt.slice(0, -8);

      var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      var now = new Date(dt_txt);
      document.querySelector(".tort").innerText = days[now.getDay()];
    },

    displayWeather5: function (data) {
      const { temp} = data.list[39].main;
      const {dt_txt} = data.list[39];
      const { icon} = data.list[39].weather[0];

      document.querySelector(".fifth").innerText = temp + "°C";
      document.querySelector(".icon5").src = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
      document.querySelector(".date5").innerText = dt_txt.slice(0, -8);

      var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      var now = new Date(dt_txt);
      document.querySelector(".besh").innerText = days[now.getDay()];
    },

    search: function () {
      this.fetchWeather(document.querySelector(".search-bar").value);
    },
  };
  
  document.querySelector(".search button").addEventListener("click", function () {
    weather.search();
    document.querySelector(".search-bar").value = ""
  });
  
  document
    .querySelector(".search-bar")
    .addEventListener("keyup", function (event) {
      if (event.key == "Enter") {
        weather.search();
        document.querySelector(".search-bar").value = ""
      }
    });
  
  weather.fetchWeather("Bishkek");