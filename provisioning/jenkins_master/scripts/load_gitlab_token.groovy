@Grab('io.github.http-builder-ng:http-builder-ng-core:0.17.0')

import static groovyx.net.http.HttpBuilder.configure
import static groovyx.net.http.ContentTypes.JSON
import groovyx.net.http.*
import static groovy.json.JsonOutput.prettyPrint
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;
import com.dabsquared.gitlabjenkins.connection.GitLabApiTokenImpl;
import hudson.util.Secret;

def result = configure {
    request.uri = 'http://gitlab'
    request.contentType = JSON[0]
}.post {
    request.uri.path = '/api/v4/session'
    request.body = [login: 'root', password: 'mypassw0rd']
    request.contentType = 'application/x-www-form-urlencoded'
    request.encoder 'application/x-www-form-urlencoded', NativeHandlers.Encoders.&form
}

def hasCreds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(GitLabApiTokenImpl.class).findAll { it.id == "gitlab-token" }.size();
if (!hasCreds) {
    Credentials c = (Credentials) new GitLabApiTokenImpl(CredentialsScope.GLOBAL,"gitlab-token", "GitLab token", Secret.fromString(result.private_token))
    SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)    
}
