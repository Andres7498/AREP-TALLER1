function getData() {
    let vector_result = {};
    var word_share_traded = document.getElementById("word_share_traded").value;
    let time_series = "" ;
    document.getElementByName("Time_Series_Daily").checked ? time_series = "TIME_SERIES_DAILY" : "";
    fetch('http://www.omdbapi.com/?i=tt3896198&apikey=925c120b')
        .then(results => results.json()).then(data => vector_result = data).then(data => {
        var jsonStr = JSON.stringify(data, null, 4);
        const table_elements = document.querySelector('#table_elements')
        delete data ['Meta Data'];
        table_elements.innerHTML = jsonStr;
    })
}
