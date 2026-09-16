(()=>{

    console.log("Loaded......");
    
    const searchBar=document.querySelector('.search-bar');
    const cards=document.querySelectorAll('.card');

    searchBar.addEventListener('keyup',()=>{
        console.log("Triggered.");
        

       const searchText= searchBar.value.toLowerCase().trim();

       cards.forEach((items)=>{

        const productName= items.querySelector('.product-name').innerHTML.toLowerCase();

        items.style.display=productName.includes(searchText)?'block':'none';
       });


    });
})
()