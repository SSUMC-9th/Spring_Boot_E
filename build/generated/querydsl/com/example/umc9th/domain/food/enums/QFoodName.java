package com.example.umc9th.domain.food.enums;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodName is a Querydsl query type for FoodName
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodName extends EntityPathBase<FoodName> {

    private static final long serialVersionUID = 106725478L;

    public static final QFoodName foodName = new QFoodName("foodName");

    public final com.example.umc9th.domain.common.QBaseEntity _super = new com.example.umc9th.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFoodName(String variable) {
        super(FoodName.class, forVariable(variable));
    }

    public QFoodName(Path<? extends FoodName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodName(PathMetadata metadata) {
        super(FoodName.class, metadata);
    }

}

