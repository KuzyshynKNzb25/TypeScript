namespace $simar {
    const categoryTemplate: string = "category-snippet.html";
    const allCategories: string = "categories.json";
    const itemTemplate: string = "item-snippet.html";
    const itemsFolder: string = "items";

    interface Category {
        name: string;
        shortname: string;
        notes: string;
        image_name: string;
    }

    interface CategoryItem {
        name: string;
        shortname: string;
        description: string;
        image_path: string;
    }

    interface CategoryData {
        categoryName: string;
        categoryItems: CategoryItem[];
    }

    const innerHtml = function (selector: string, html: string): void {
        const targetHtml = document.querySelector(selector) as HTMLElement;
        targetHtml.innerHTML = html;
    };

    const insertProperty = function (string: string, propName: string, propValue: string): string {
        const propToReplace = "{{" + propName + "}}";
        return string.replace(new RegExp(propToReplace, "g"), propValue);
    };

    document.addEventListener("DOMContentLoaded", function () {
        $ajaxUtils.sendGetRequest(
            categoryTemplate,
            function (responseText: string) {
                innerHtml("#content-place", responseText);
            },
            false
        );
    });

    function buildAndShowCategoriesHTML(categories: Category[]): void {
        $ajaxUtils.sendGetRequest(
            categoryTemplate,
            function (categoryTemplateHtml: string) {
                const categoriesViewHtml = buildCategoriesViewHtml(categories, categoryTemplateHtml);
                innerHtml("#content-place", categoriesViewHtml);
            },
            false
        );
    }

    function buildCategoriesViewHtml(categories: Category[], categoryTemplateHtml: string): string {
        let finalHtml = "<div>";
        
        for (let i = 0; i < categories.length; i++) {
            let html = categoryTemplateHtml;
            html = insertProperty(html, "name", categories[i].name);
            html = insertProperty(html, "shortname", categories[i].shortname);
            html = insertProperty(html, "notes", categories[i].notes);
            html = insertProperty(html, "image_name", categories[i].image_name);
            finalHtml += html;
        }
        
        let secret = Math.floor(Math.random() * categories.length);
        let html = categoryTemplateHtml;
        html = insertProperty(html, "name", categories[secret].name);
        html = insertProperty(html, "shortname", "Specials");
        html = insertProperty(html, "notes", "Випадкова категорія");
        html = insertProperty(html, "image_name", "particle-style-question-mark-background-design-query_1017-43011.avif");
        finalHtml += html;
        
        finalHtml += "</div>";
        return finalHtml;
    }

    export function loadCatalogCatagories(): void {
        $ajaxUtils.sendGetRequest(allCategories, buildAndShowCategoriesHTML);
    }

    function buildAndShowItemsHTML(items: CategoryData): void {
        $ajaxUtils.sendGetRequest(
            itemTemplate,
            function (itemTemplateHtml: string) {
                const itemsViewHtml = buildItemsViewHtml(items, itemTemplateHtml);
                innerHtml("#content-place", itemsViewHtml);
            },
            false
        );
    }

    function buildItemsViewHtml(items: CategoryData, templateHtml: string): string {
        let finalHtml = "<div>";
        finalHtml += "<h4>" + items.categoryName + "</h4>";
        
        for (let i = 0; i < items.categoryItems.length; i++) {
            let html = templateHtml;
            html = insertProperty(html, "name", items.categoryItems[i].name);
            html = insertProperty(html, "description", items.categoryItems[i].description);
            html = insertProperty(html, "image_name", items.categoryItems[i].image_path);
            finalHtml += html;
        }
        
        finalHtml += "</div>";
        return finalHtml;
    }

    export function loadCatalogItems(categoryName: string): void {
        $ajaxUtils.sendGetRequest(itemsFolder + "/" + categoryName + '.json', buildAndShowItemsHTML);
    }
}

// Початковий виклик
$simar.loadCatalogCatagories();