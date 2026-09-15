(()=>{
    console.log("FAQ JS loaded");
    // let faqIcon= document.getElementsByClassName('faq-icon')[0];
    // let faIconNegative=document.getElementsByClassName('faq-icon2')[0];
    // let answer=document.getElementsByClassName('faq-answer')[0];

    // let iconFlag=true;

    // faqIcon.addEventListener('click',()=>{

        

    //     if(iconFlag){
    //         faqIcon.innerHTML='-';
    //         answer.style.display='block';
    //         iconFlag=false;
    //     } else{
    //         faqIcon.innerHTML='+';
    //         answer.style.display='none';
    //         iconFlag=true;
    //     }
    // });


    document.querySelectorAll('.faq-items').forEach((item)=>{
        const faqIcon = item.querySelector('.faq-icon');
        const answer = item.querySelector('.faq-answer');

        if(!faqIcon || !answer) return;

        let flag= false;

        faqIcon.addEventListener('click',()=>{

            flag=!flag;
            faqIcon.innerHTML=flag? '-': '+';
            answer.style.display=flag?"block": "none";
        })

    });
})
()