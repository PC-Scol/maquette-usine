import com.dabsquared.gitlabjenkins.connection.*
import jenkins.model.Jenkins

GitLabConnectionConfig descriptor = (GitLabConnectionConfig) Jenkins.getInstance().getDescriptor(GitLabConnectionConfig.class)

GitLabConnection gitLabConnection = new GitLabConnection('GitLab',
                                        'http://192.168.50.10/',
                                        'gitlab-token',
                                        false,
                                        10,
                                        10)
descriptor.getConnections().clear()
descriptor.addConnection(gitLabConnection)
descriptor.save()
