/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// variables 
var imgFeature = document.querySelector('.img-feature');
var listImage = document.querySelectorAll('.list-image img');
var preBtn = document.querySelector('.prev');
var nextBtn = document.querySelector('.next');
// index of img in list img
var currentIndex = 0;
// update main photo
function updateImageByIndex(index) {
    // remove active
    document.querySelectorAll('.list-image div').forEach(item => {
        item.classList.remove('active');
    });

    currentIndex = index;
    imgFeature.src = listImage[index].getAttribute('src');
    listImage[index].parentElement.classList.add('active');

}
// click on the photo in list img
listImage.forEach((imgElement, index) => {
    imgElement.addEventListener('click', e => {
        imgFeature.style.opacity = '0';

        setTimeout(() => {
            updateImageByIndex(index);
            imgFeature.style.opacity = '1';
        }, 400);

    });
});
// button previous img
preBtn.addEventListener('click', e => {
    imgFeature.style.animation = '';
    if (currentIndex == 0) {
        currentIndex = listImage.length - 1;
    } else {
        currentIndex--;
    }
    setTimeout(() => {
        updateImageByIndex(currentIndex);
        imgFeature.style.animation = 'slideLeft 1s ease-in-out forwards';
    }, 200);
});
// button next img
nextBtn.addEventListener('click', e => {
    imgFeature.style.animation = '';
    if (currentIndex == listImage.length - 1) {
        currentIndex = 0;
    } else {
        currentIndex++;
    }
    setTimeout(() => {
        updateImageByIndex(currentIndex);
        imgFeature.style.animation = 'slideRight 1s ease-in-out forwards';
    }, 200);
});

updateImageByIndex(0);

