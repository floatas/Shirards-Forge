using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.ApplicationInsights.AspNetCore.Extensions;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Routing;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace Shirard
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseBrowserLink();
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }

            app.UseStaticFiles();

            app.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "default",
                    template: "{controller=Home}/{action=Index}/{id?}");
                //routes.MapPost(template: "2/1.pl", handler: echo);
            });
        }

        public Task echo(HttpRequest request, HttpResponse response, RouteData route)
        {

            var builder = new StringBuilder();

            var body = new StreamReader(request.Body).ReadToEnd();

            var memoryStream = new MemoryStream();
            var binary = new BinaryWriter(memoryStream);

            binary.Write(BitConverter.GetBytes((ushort)47806).Reverse().ToArray());//Magic number = 47806
            //48826
            binary.Write((byte)0); //c
            binary.Write((byte)1); //Separator
            binary.Write((int)123);//Size of response J+=
            binary.Write((ushort)1); //var42
            binary.Write((byte)1); //var11
            binary.Write((byte)1); //var12
            binary.Write((ushort)1); //var13
            binary.Write((byte)1); //var14

            response.Body.Write(memoryStream.ToArray(), 0, (int)memoryStream.Length);
            //memoryStream.CopyTo(response.Body);
            //memoryStream.Close();


            return Task.CompletedTask;
        }

    }

    // http://prod4.eraofeidolon.com/2/1.pl


    //public class LogRequestsMiddleWare : IMiddleware
    //{
    //    public Task InvokeAsync(HttpContext context, RequestDelegate next)
    //    {
    //        var logger = NLog.LogManager.GetCurrentClassLogger();

    //        //var request = JsonConvert.SerializeObject(context.Request, Formatting.Indented, new JsonSerializerSettings { MaxDepth = 1, ReferenceLoopHandling = ReferenceLoopHandling.Ignore });
    //        var builder = new StringBuilder();

    //        builder.AppendLine(context.Request.GetUri().ToString());
    //        builder.AppendLine("ContentType:" + context.Request.ContentType);
    //        requestBody = new StreamReader(context.Request.Body).ReadToEnd();
    //        builder.AppendLine("Body:" + requestBody);
    //        builder.AppendLine("Protocol:" + context.Request.Protocol);
    //        builder.AppendLine("Headers:" + string.Join(", ", context.Request.Headers.Select(x => x.Key + "->" + x.Value)));
    //        builder.AppendLine("QueryString:" + context.Request.QueryString);

    //        logger.Debug(builder.ToString());

    //        return next(context);
    //    }
    //}
}
