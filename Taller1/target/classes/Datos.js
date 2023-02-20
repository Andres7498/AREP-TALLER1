function loadGetMsg() {
    console.log("ANDREEEEEEEEEEEEEEEEEEEEEEE");
    let vector_result = {};
    let nameVar = document.getElementById("name").value;
    fetch('https://www.omdbapi.com/?t=/%22+nameVar+/%22&apikey=925c120b/%22);/n%22%27)
        .then(results => results.json()).then(data => vector_result = data).then(data => {
        var jsonStr = JSON.stringify(data, null, 4);
        const table_elements = document.querySelector('#table_elements')
        delete data ['Meta Data'];
        table_elements.innerHTML = jsonStr;
    })
}
}
