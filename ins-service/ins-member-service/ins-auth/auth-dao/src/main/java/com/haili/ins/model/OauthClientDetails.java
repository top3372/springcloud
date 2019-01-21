package com.haili.ins.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * Table: oauth_client_details
 */
@Table(name = "`oauth_client_details`")
@Data
public class OauthClientDetails implements Serializable {
    /**
     * Table:     oauth_client_details
     * Column:    client_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`client_id`")
    private String clientId;

    /**
     * Table:     oauth_client_details
     * Column:    resource_ids
     * Nullable:  true
     */
    @Column(name = "`resource_ids`")
    private String resourceIds;

    /**
     * Table:     oauth_client_details
     * Column:    client_secret
     * Nullable:  true
     */
    @Column(name = "`client_secret`")
    private String clientSecret;

    /**
     * Table:     oauth_client_details
     * Column:    scope
     * Nullable:  true
     */
    @Column(name = "`scope`")
    private String scope;

    /**
     * Table:     oauth_client_details
     * Column:    authorized_grant_types
     * Nullable:  true
     */
    @Column(name = "`authorized_grant_types`")
    private String authorizedGrantTypes;

    /**
     * Table:     oauth_client_details
     * Column:    web_server_redirect_uri
     * Nullable:  true
     */
    @Column(name = "`web_server_redirect_uri`")
    private String webServerRedirectUri;

    /**
     * Table:     oauth_client_details
     * Column:    authorities
     * Nullable:  true
     */
    @Column(name = "`authorities`")
    private String authorities;

    /**
     * Table:     oauth_client_details
     * Column:    access_token_validity
     * Nullable:  true
     */
    @Column(name = "`access_token_validity`")
    private Integer accessTokenValidity;

    /**
     * Table:     oauth_client_details
     * Column:    refresh_token_validity
     * Nullable:  true
     */
    @Column(name = "`refresh_token_validity`")
    private Integer refreshTokenValidity;

    /**
     * Table:     oauth_client_details
     * Column:    additional_information
     * Nullable:  true
     */
    @Column(name = "`additional_information`")
    private String additionalInformation;

    /**
     * Table:     oauth_client_details
     * Column:    autoapprove
     * Nullable:  true
     */
    @Column(name = "`autoapprove`")
    private String autoapprove;

    private static final long serialVersionUID = 1L;
}