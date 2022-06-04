$(document).ready(function() {
    $(".minusButton").on("click", function (evt) {
        evt.preventDefault();
        let productId = $(this).attr("pid");
        let qtyInput = $("#quantity" + productId);

        $(qtyInput).focus();

        let newQty = parseInt(qtyInput.val()) - 1;
        if (newQty > 0) {
            qtyInput.val(newQty);
        }
    });

    $(".plusButton").on("click", function (evt) {
        evt.preventDefault();
        let productId = $(this).attr("pid");
        let qtyInput = $("#quantity" + productId);

        $(qtyInput).focus();

        let newQty = parseInt(qtyInput.val()) + 1;
        if (newQty < 10) {
            qtyInput.val(newQty);
        }
    });
});