package hu.gab.wiki.client.admin.article.widget;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.paper.widget.PaperInput;
import hu.gab.wiki.client.admin.article.ArticleAdminPresenter;
import hu.gab.wiki.client.widgets.SavableBootstrapModal;

/**
 * @author PG
 * @since 2016-05-20
 */
public class CreateArticleWidget implements SavableBootstrapModal.ModalSaveClickHandler {
    private SavableBootstrapModal savableBootstrapModal;
    private ArticleAdminPresenter presenter;

    public CreateArticleWidget(ArticleAdminPresenter presenter) {
        this.presenter = presenter;
        savableBootstrapModal = createSaveableModal();
    }

    private PaperInput articleName;
    private PaperInput articleSlug;

    private SavableBootstrapModal createSaveableModal() {
        Label header = new Label("Create an article");

        HTMLPanel form = new HTMLPanel("");

        articleName = new PaperInput();
        articleName.setPlaceholder("Article name");

        articleSlug = new PaperInput();
        articleSlug.setPlaceholder("Article slug");

        form.add(articleName);
        form.add(articleSlug);

        return new SavableBootstrapModal(header, form, this);
    }

    public void show() {
        savableBootstrapModal.show();
    }

    public void hide() {
        savableBootstrapModal.hide();
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
