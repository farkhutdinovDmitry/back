package ru.dfarkhutdinov.back.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dfarkhutdinov.back.db.CategoryRepository;
import ru.dfarkhutdinov.back.entity.Category;

import java.util.Optional;

@Route("manageCategory")
public class ManageCategory extends AppLayout implements HasUrlParameter<Integer> {

    Long id;
    FormLayout categoryForm;
    TextField name;
    TextField reqName;
    Button saveCategory;

    @Autowired
    CategoryRepository categoryRepository;

    public ManageCategory() {
        categoryForm = new FormLayout();
        name = new TextField("Name");
        reqName = new TextField("ReqName");
        saveCategory = new Button("Save");

        categoryForm.add(name, reqName, saveCategory);
        setContent(categoryForm);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer contactId) {
        id = (long) contactId;
        if (id != 0) {
            addToNavbar(new H3("Edit"));
        } else {
            addToNavbar(new H3("Create new category"));
        }
        fillForm();
    }


    public void fillForm() {
        if (id != 0) {
            Optional<Category> category = categoryRepository.findById(id);
            category.ifPresent(x -> {
                name.setValue(x.getName());
                reqName.setValue(x.getReqName());
            });
        }

        saveCategory.addClickListener(clickEvent -> {
            Category category = new Category();
            category.setName(name.getValue());
            category.setReqName(reqName.getValue());
            category.setDeleted(false);
            categoryRepository.save(category);

            Notification notification = new Notification("Category edited", 1000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> {
                UI.getCurrent().navigate(MainWindow.class);
            });
            categoryForm.setEnabled(false);
            notification.open();
        });
    }
}

