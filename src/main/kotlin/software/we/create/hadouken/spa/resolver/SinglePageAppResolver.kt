package software.we.create.hadouken.spa.resolver

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.util.StringUtils
import org.springframework.web.servlet.resource.ResourceResolver
import org.springframework.web.servlet.resource.ResourceResolverChain
import java.io.IOException
import javax.servlet.http.HttpServletRequest

class SinglePageAppResolver : ResourceResolver {

    private val index: Resource = ClassPathResource("static/index.html")
    private val extensions: List<String> = listOf("html", "js", "json", "csv", "css", "png", "svg", "eot", "ttf", "woff", "appcache", "jpg", "jpeg", "gif", "ico")
    private val ignoredPaths: List<String> = listOf("api")

    override fun resolveResource(request: HttpServletRequest?, requestPath: String, locations: MutableList<out Resource>, chain: ResourceResolverChain): Resource? {
        return resolve(requestPath, locations)
    }

    override fun resolveUrlPath(resourcePath: String, locations: MutableList<out Resource>, chain: ResourceResolverChain): String? {
        val resource: Resource? = resolve(resourcePath, locations)

        return if (resource != null) {
            try {
                resource.url.toString()
            } catch (e: IOException) {
                resource.filename
            }
        } else null
    }

    private fun resolve(requestPath: String, locations: MutableList<out Resource>): Resource? {
        if (ignoredPaths.contains(requestPath)) return null

        return if (isHandled(requestPath)) {
            locations.stream()
                    .map { curResource -> createRelative(curResource, requestPath) }
                    .filter({ curResource -> curResource != null && curResource.exists() })
                    .findFirst()
                    .orElse(null)
        } else index
    }

    private fun isHandled(requestPath: String): Boolean {
        val extension: String? = StringUtils.getFilenameExtension(requestPath)
        return extensions.stream().anyMatch({ curExtension: String -> curExtension == extension })
    }

    private fun createRelative(resource: Resource, requestPath: String): Resource? {
        return try {
            resource.createRelative(requestPath)
        } catch (e: IOException) {
            return null
        }
    }
}