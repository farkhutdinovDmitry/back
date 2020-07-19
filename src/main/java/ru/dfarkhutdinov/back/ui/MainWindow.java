package ru.dfarkhutdinov.back.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("main")
public class MainWindow extends AppLayout {

    private VerticalLayout layout;
    private Tabs tabs;


    public MainWindow() {
        layout = new VerticalLayout();
        tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        //val item = menu.addItem("Categories");
        //menu.addItem("Banners");
        Tab tab = new Tab();
        RouterLink routerLink = new RouterLink(null, CategoryView.class);
        routerLink.setClassName("menu-link");
        routerLink.add(new Span("categories"));
        tab.add(routerLink);
        tabs.add(tab);
        tabs.setSelectedTab(tab);
        layout.add(tabs);
        setContent(layout);
    }
}

