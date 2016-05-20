package hu.gab.wiki.client.admin.article;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.vaadin.polymer.paper.widget.PaperButton;
import hu.gab.wiki.client.mvp.WikiView;
import hu.gab.wiki.shared.dto.articleadmin.DTO_ArticleSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PG
 * @since 2016-05-15
 */
public class ArticleAdminView extends WikiView<ArticleAdminPresenter> implements IArticleAdminView {


    interface ArticleAdminActivityUiBinder extends UiBinder<Widget, ArticleAdminView> {
    }

    private static ArticleAdminActivityUiBinder ourUiBinder = GWT.create(ArticleAdminActivityUiBinder.class);

    @UiField
    PaperButton buttonAddNewArticle;

    @UiField
    HTMLPanel tableContainer;

    private CellTable<DTO_ArticleSummary> cellTable = null;

    @Override
    protected void afterActivitySet() {
        addEventHandlers();

        cellTable.setRowData(0, new ArrayList<>());
        activity.refreshArticles();
    }

    public ArticleAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        createCellTable();
    }

    private void createCellTable() {
        cellTable = new CellTable<>();

        cellTable.addStyleName("table table-hover");
        cellTable.setPageSize(10);
        cellTable.setPageStart(0);

        AsyncDataProvider<DTO_ArticleSummary> asyncDataProvider = new AsyncDataProvider<DTO_ArticleSummary>() {
            @Override
            protected void onRangeChanged(HasData<DTO_ArticleSummary> display) {
            }
        };

        asyncDataProvider.addDataDisplay(cellTable);

        cellTable.addColumn(new TextColumn<DTO_ArticleSummary>() {
            @Override
            public String getValue(DTO_ArticleSummary object) {
                if (object != null) {
                    return String.valueOf(object.getId());
                }
                return "";
            }
        }, "Id");

        cellTable.addColumn(new TextColumn<DTO_ArticleSummary>() {
            @Override
            public String getValue(DTO_ArticleSummary object) {
                if (object != null) {
                    return object.getSlug();
                }
                return "";
            }
        }, "Slug");

        cellTable.addColumn(new TextColumn<DTO_ArticleSummary>() {
            @Override
            public String getValue(DTO_ArticleSummary object) {
                if (object != null) {
                    return object.getCurrentName();
                }
                return "";
            }
        }, "Name");


        Column<DTO_ArticleSummary, String> detailsButtonCol = new Column<DTO_ArticleSummary, String>(new ButtonCell()) {
            @Override
            public String getValue(DTO_ArticleSummary object) {
                return "Részletek";
            }
        };

        detailsButtonCol.setFieldUpdater((index, object, value) -> {
            onArticleDetailsClick(object);
        });
        cellTable.addColumn(detailsButtonCol, "Részletek");

        tableContainer.add(cellTable);
    }



    private void onArticleDetailsClick(DTO_ArticleSummary dtoArticleSummary) {
        Window.alert("Detals clicked");
    }

    private void addEventHandlers() {
        buttonAddNewArticle.addClickHandler(event -> {
            activity.triggerCreateArticleModal();
        });
    }

    @Override
    public void setArticleSummaries(List<DTO_ArticleSummary> articleSummaryList) {
        cellTable.setRowData(0, articleSummaryList);
    }
}