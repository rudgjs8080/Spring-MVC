document.addEventListener("DOMContentLoaded", () =>{
    document.querySelector("tr.list_tr").addEventListener("click", () =>{
        let tagName = e.target.tagName;
        if(tagName == "TD"){
            let urlPath = `${rootPath}`;
            let tr = e.target.closest("TR").dataset;
            alert("TR")
        }
    })
})