package hu.gab.wiki.client.admin.article.widget;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.admin.article.ArticleAdminPresenter;
import hu.gab.wiki.client.widgets.SaveableBootstrapModal;

/**
 * @author PG
 * @since 2016-05-20
 */
public class CreateArticleWidget implements SaveableBootstrapModal.ModalSaveClickHandler {
    private SaveableBootstrapModal saveableBootstrapModal;
    private ArticleAdminPresenter presenter;

    public CreateArticleWidget(ArticleAdminPresenter presenter) {
        this.presenter = presenter;
        saveableBootstrapModal = createSaveableModal();
    }

    private PaperInput articleName;
    private PaperInput articleSlug;

    private SaveableBootstrapModal createSaveableModal() {
        Label header = new Label("Create an article");

        HTMLPanel form = new HTMLPanel("");

        articleName = new PaperInput();
        articleName.setPlaceholder("Article name");

        articleSlug = new PaperInput();
        articleSlug.setPlaceholder("Article slug");

        form.add(articleName);
        form.add(articleSlug);

        return new SaveableBootstrapModal(header, form, this);
    }

    public void show() {
        saveableBootstrapModal.show();
    }

    public void hide() {
        saveableBootstrapModal.hide();
    }

    public PaperInput getArticleName() {
        return articleName;
    }

    public PaperInput getArticleSlug() {
        return articleSlug;
    }

    @Override
    public void onSaveClick() {
        presenter.onCreateArticle(this);
    }
}
