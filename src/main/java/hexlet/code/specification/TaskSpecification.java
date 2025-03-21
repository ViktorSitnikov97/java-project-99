package hexlet.code.specification;

import hexlet.code.dto.tasks.TaskParamsDTO;

import hexlet.code.model.Task;

import jakarta.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public class TaskSpecification {
    public Specification<Task> build(TaskParamsDTO params) {
        return withTitleCont(params.getTitleCont())
                .and(withAssigneeId(params.getAssigneeId()))
                .and(withStatus(params.getStatus()))
                .and(withLabel(params.getLabelId()));
    }

    private Specification<Task> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + titleCont.toLowerCase() + "%"
                );
    }

    private Specification<Task> withAssigneeId(Long id) {
        return (root, query, criteriaBuilder) -> id == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("assignee").get("id"), id);
    }

    private Specification<Task> withStatus(String slug) {
        return (root, query, criteriaBuilder) -> slug == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("taskStatus").get("slug"), slug);
    }

    private Specification<Task> withLabel(Long id) {
        return (root, query, criteriaBuilder) -> id == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.join("labels", JoinType.INNER).get("id"), id);
    }
}
