package ru.dfarkhutdinov.back.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dfarkhutdinov.back.entity.Category;
import ru.dfarkhutdinov.back.service.CategoryService;

import java.util.List;

@Route(layout = MainWindow.class)
public class CategoryView extends Div {
    private Grid<Category> grid;

    @Autowired
    CategoryService categoryService;

    public CategoryView() {
        grid = new Grid<>();
        fillGrid();
        add(grid);
    }

    public void fillGrid() {
        List<Category> categories = categoryService.getAllCategories();
        if (!categories.isEmpty()) {

            grid.addColumn(Category::getName).setHeader("Name");
            grid.addColumn(Category::getReqName).setHeader("ReqName");

            grid.addColumn(new NativeButtonRenderer<>("Edit", category -> {
                UI.getCurrent().navigate(ManageCategory.class, category.getId().intValue());
            }));
            grid.addColumn(new NativeButtonRenderer<>("Delete", category -> {
                Dialog dialog = new Dialog();
                Button confirm = new Button("Delete");
                Button cancel = new Button("Cancel");
                dialog.add("Are you sure want to delete?");
                dialog.add(confirm);
                dialog.add(cancel);

                confirm.addClickListener(clickEvent -> {
                    categoryService.deleteCategoryById(category.getId());
                    dialog.close();
                    Notification notification = new Notification("Category deleted", 1000);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    grid.setItems(categoryService.getAllCategories());

                });
                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });
                dialog.open();
            }));

            grid.setItems(categories);
        }
    }

}
