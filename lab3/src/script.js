var $simar;
(function ($simar) {
    var categoryTemplate = "category-snippet.html";
    var allCategories = "categories.json";
    var itemTemplate = "item-snippet.html";
    var itemsFolder = "items";
    var innerHtml = function (selector, html) {
        var targetHtml = document.querySelector(selector);
        if (targetHtml) {
            targetHtml.innerHTML = html;
        }
    };
    var insertProperty = function (string, propName, propValue) {
        var propToReplace = "{{" + propName + "}}";
        return string.replace(new RegExp(propToReplace, "g"), propValue);
    };
    document.addEventListener("DOMContentLoaded", function () {
        $ajaxUtils.sendGetRequest(categoryTemplate, function (responseText) {
            innerHtml("#content-place", responseText);
        }, false);
    });
    function buildAndShowCategoriesHTML(categories) {
        $ajaxUtils.sendGetRequest(categoryTemplate, function (categoryTemplateHtml) {
            var categoriesViewHtml = buildCategoriesViewHtml(categories, categoryTemplateHtml);
            innerHtml("#content-place", categoriesViewHtml);
        }, false);
    }
    function buildCategoriesViewHtml(categories, categoryTemplateHtml) {
        var finalHtml = "<div>";
        for (var i = 0; i < categories.length; i++) {
            var html_1 = categoryTemplateHtml;
            html_1 = insertProperty(html_1, "name", categories[i].name);
            html_1 = insertProperty(html_1, "shortname", categories[i].shortname);
            html_1 = insertProperty(html_1, "notes", categories[i].notes);
            html_1 = insertProperty(html_1, "image_name", categories[i].image_name);
            finalHtml += html_1;
        }
        // Випадкова категорія (Specials)
        var secret = Math.floor(Math.random() * categories.length);
        var html = categoryTemplateHtml;
        html = insertProperty(html, "name", categories[secret].name);
        html = insertProperty(html, "shortname", "Specials");
        html = insertProperty(html, "notes", "Випадкова категорія");
        html = insertProperty(html, "image_name", "particle-style-question-mark-background-design-query_1017-43011.avif");
        finalHtml += html;
        finalHtml += "</div>";
        return finalHtml;
    }
    function loadCatalogCatagories() {
        $ajaxUtils.sendGetRequest(allCategories, buildAndShowCategoriesHTML);
    }
    $simar.loadCatalogCatagories = loadCatalogCatagories;
    function buildAndShowItemsHTML(items) {
        $ajaxUtils.sendGetRequest(itemTemplate, function (itemTemplateHtml) {
            var itemsViewHtml = buildItemsViewHtml(items, itemTemplateHtml);
            innerHtml("#content-place", itemsViewHtml);
        }, false);
    }
    function buildItemsViewHtml(items, templateHtml) {
        var finalHtml = "<div>";
        finalHtml += "<h4>" + items.categoryName + "</h4>";
        for (var i = 0; i < items.categoryItems.length; i++) {
            var html = templateHtml;
            html = insertProperty(html, "name", items.categoryItems[i].name);
            html = insertProperty(html, "description", items.categoryItems[i].description);
            html = insertProperty(html, "image_name", items.categoryItems[i].image_path);
            finalHtml += html;
        }
        finalHtml += "</div>";
        return finalHtml;
    }
    function loadCatalogItems(categoryName) {
        $ajaxUtils.sendGetRequest(itemsFolder + "/" + categoryName + '.json', buildAndShowItemsHTML);
    }
    $simar.loadCatalogItems = loadCatalogItems;
})($simar || ($simar = {}));
// Початковий виклик
$simar.loadCatalogCatagories();
